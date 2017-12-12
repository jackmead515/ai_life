from flask import Flask
from flask import request
from flask import render_template
from ai import AI

global brain
brain = AI(3000, 11, 1000, 0.9, 0.005, 2);
app = Flask(__name__)

#-------------------------------------------------------------------------------------
@app.route('/', methods = ['POST'])
def api():
    content = request.get_json()
    state = content['state']
    reward = content['reward']
    if state is None or reward is None:
        return '0';
    else:
        action = brain.update(reward, state)
        print('Reward: ' + str(reward) + '  -----  Action: ' + str(action))
        return str(action)
#-------------------------------------------------------------------------------------


#-------------------------------------------------------------------------------------
@app.route('/ai', methods = ['POST'])
def create():
    content = request.get_json()
    hNeurons = content['hNeurons']
    learnRate = content['learnRate']
    temp = content['temp']
    gamma = content['gamma']

    brain = AI(3000, 11, hNeurons, gamma, learnRate, temp)
    print('New AI created!')

    response = flask.jsonify({'status': '200'})
    return response
#-------------------------------------------------------------------------------------


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)


















#-------------------------------------------------------------------------------------
