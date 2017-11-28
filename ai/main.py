from flask import Flask
from flask import request
from flask import render_template
from ai import AI

brain = AI(3000, 11, 0.9)
app = Flask(__name__)

#-------------------------------------------------------------------------------------
def handle_get():
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
#def handle_get():
#    return render_template('index.html')


#-------------------------------------------------------------------------------------
def handle_create():
    inputNeurons = int(request.args.get('input'))
    brain = AI(inputNeurons, 11, 0.9)
    print('AI created: ' + str(inputNeurons) + ' inputs')
    return 'status=1'


#-------------------------------------------------------------------------------------
@app.route('/', methods = ['POST'])
def api():
    return handle_get()


#-------------------------------------------------------------------------------------
@app.route('/create', methods = ['POST'])
def create():
    return handle_create()

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)


















#-------------------------------------------------------------------------------------
