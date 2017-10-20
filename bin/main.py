from flask import Flask
from flask import request
from flask import render_template
from ai import Dqn

app = Flask(__name__)
brain = ai(5, 3, 0.9) #needs to be changed!

def handle_post():
    jarr = request.get_json(silent=True)
    parsed = json.loads(jarr)
    inputs = parsed['inputs'].keys()
    reward = parsed['reward']
    print inputArr
    return inputArr

def handle_get():
    return render_template('index.html')



@app.route('/', methods=['GET', 'POST'])
def root():
    if request.method == 'POST':
        return handle_post()
    else:
        return handle_get()


if __name__ == '__main__':
   app.run(debug=True)
