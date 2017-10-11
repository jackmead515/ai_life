from flask import Flask
from flask import request
#from ai import AI

#brain = AI(500, 11, 0.9)
app = Flask(__name__)

#-------------------------------------------------------------------------------------
def handle_post():
    json = request.get_json(silent = True)
    state = json.get('state')
    reward = json.get('reward')
    if state is None or reward is None:
        return jsonify({"err": "400"})
    else:
        return 'hello :)' #TODO


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
