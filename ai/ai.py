import ast #read multi-dimensional array from string
import numpy as np #used to manipulate array data
import random #used to take random data
import os #used to load and save brain models
import torch #used for AI stuff
import torch.nn as nn #neural network
import torch.nn.functional as F
import torch.optim as optim #stosatic gradient desent and other optimizers
import torch.autograd as autograd
from torch.autograd import Variable

################################################################################################################
################################################################################################################
#Architecture of the Neural Network
class Network(nn.Module): #extends neural networks module class functions

    #-----------------------------------------------------------------------------------------------------------
    def __init__(self, inputNeurons, outputNeurons):
        super(Network, self).__init__()
        self.inputNeurons = inputNeurons
        self.outputNeurons = outputNeurons

        self.c1 = nn.Linear(inputNeurons, 30)

        self.c2 = nn.Linear(30, outputNeurons)

    #-----------------------------------------------------------------------------------------------------------
    def forward(self, state):
        x = F.relu(self.c1(state))
        q_values = self.c2(x)
        return q_values

################################################################################################################
################################################################################################################
#Implementing experience replay
class Memory(object):

    #-----------------------------------------------------------------------------------------------------------
    def __init__(self, capacity):
        self.capacity = capacity #amount of memories to store
        self.memory = [] #actual memory list

    #-----------------------------------------------------------------------------------------------------------
    def append(self, event):
        #event: last state, new state, last action, last reward
        self.memory.append(event)
        if len(self.memory) > self.capacity:
            del self.memory[0]

    #-----------------------------------------------------------------------------------------------------------
    def sample(self, batch_size):
        #random.sample takes random chunks of the memory equal to the batch_size
        #zip(*list) [[1,2,3], [4,5,6]] => [[1,4], [2,5], [3,6]]
        #we want to pair the memories' event's data types.
        samples = zip(*random.sample(self.memory, batch_size))
        #map iterates over all of the elements of samples
        #the lambda function takes the arrays or Tensors from samples and converts them
        #into torch variables.
        return map(lambda x: Variable(torch.cat(x, 0)), samples)

################################################################################################################
################################################################################################################
#Implementing Deep Q Learning
class AI():

    #-----------------------------------------------------------------------------------------------------------
    def __init__(self, inputNeurons, outputNeurons, gamma):
        self.gamma = gamma
        self.reward_window = [] #mean of last 100 rewards to print out to screen
        self.model = Network(inputNeurons, outputNeurons)
        self.memory = ReplayMemory(100000)
        self.optimizer = optim.Adam(self.model.parameters(), lr = 0.001)
        self.last_state = torch.Tensor(inputNeurons).unsqueeze(0) #adding a fake dimension? Whhhaaaa
        self.last_action = 0
        self.last_reward = 0

    #-----------------------------------------------------------------------------------------------------------
    def select_action(self, state): # state is defined at what comes out of the output neurons "Q values"
        # Tempurature parameter T=7
        # softmax([1,2,3]) => [0.04,0.11,0.85]   softmax([1,2,3]*3) => [0, 0.02, 0.98]
        # increases the rate at which the softmax function is certain of which action to take
        probs = F.softmax(self.model(Variable(state, volatile = True))*7)
        action = probs.multinomial() #picking a random action
        return action.data[0,0] #getting rid of fake dimension

    #-----------------------------------------------------------------------------------------------------------
    def learn(self, batch_state, batch_next_state, batch_reward, batch_action):
        outputs = self.model(batch_state).gather(1, batch_action.unsqeeze(1)).squeeze(1)
        next_outputs = self.model(batch_next_state).detach().max(1)[0]
        target = self.gamma*next_outputs + batch_reward
        temporaldiff_loss = F.smooth_l1_loss(outputs, target)
        self.optimizer.zero_grad()
        temporaldiff_loss.backward(retain_variables = True)
        self.optimizer.step()

    #-----------------------------------------------------------------------------------------------------------
    #root of ai. Takes in the state of the map: state, and the rewards from it: reward, appends it to memory,
    #selects a new action to play, learns from the reward, updates the last action, state, and reward, updates
    #reward window to show how the training is going, then finally returns the new action to play
    def update(self, reward, state):
        '''
        state = np.array(ast.literal_eval(state)).flatten()
        state = torch.Tensor(state).float().unsqueeze(0)

        self.memory.append((
            self.last_state,
            state,
            torch.LongTensor([int(self.last_action)]),
            torch.Tensor([self.last_reward])
        ))

        action = self.select_action(state)
        if len(self.memory.memory) > 100:
                batch_state, batch_next_state, batch_reward, batch_action = self.memory.sample(100)
                self.learn(batch_state, batch_next_state, batch_reward, batch_action)

        return action
        '''

        new_state = torch.Tensor(state).float().unsqueeze(0)

        self.memory.append((
                            self.last_state,
                            new_state,
                            torch.LongTensor([int(self.last_action)]),
                            torch.Tensor([self.last_reward])
                          ))

        action = self.select_action(new_state)
        if len(self.memory.memory) > 100:
                batch_state, batch_next_state, batch_reward, batch_action = self.memory.sample(100)
                self.learn(batch_state, batch_next_state, batch_reward, batch_action)
        self.last_action = action
        self.last_state = new_state
        self.last_reward = reward

        self.reward_window.append(reward)
        if len(self.reward_window) > 1000:
            del self.reward_window[0]

        return action
