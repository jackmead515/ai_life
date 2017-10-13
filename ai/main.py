from flask import Flask
from flask import request
from flask import render_template
from ai import AI

brain = AI(500, 11, 0.9)
app = Flask(__name__)

#-------------------------------------------------------------------------------------
def handle_post():
    state = request.args.get('state')
    reward = request.args.get('reward')
    if state is None or reward is None:
        return 0;
    else:
        return brain.update(reward, state)


#-------------------------------------------------------------------------------------
def handle_get():
    return render_template('index.html')


#-------------------------------------------------------------------------------------
@app.route('/', methods = ['POST', 'GET'])
def api():
    if request.method == 'POST':
        return handle_post()
    elif request.method == 'GET':
        return handle_get()

if __name__ == '__main__':
    app.run(debug = True)
