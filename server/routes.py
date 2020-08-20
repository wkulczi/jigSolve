from flask import request, send_file

from flask import Flask
import sift

app = Flask(__name__)


@app.route("/")
def home():
    return "Welcome!"


@app.route("/upload-puzzle", methods=['POST'])
def post_one_puzzle_route():
    one_puzzle = request.files['puzzle']
    filename = 'images/jeden_puzzel.png'
    one_puzzle.save(filename)
    return "You send one puzzle!"


@app.route("/upload-puzzles", methods=['POST'])
def post_entire_puzzles_route():
    entire_puzzles = request.files['picture']
    filename = 'images/cale_puzzle.png'
    entire_puzzles.save(filename)
    # print('Entire puzzles image saved')
    return "You send entire puzzles!"


@app.route("/match-puzzle", methods=['GET'])
def get_result():
    sift.process()
    return send_file('images/dopasowanie.png', mimetype='image/png')
