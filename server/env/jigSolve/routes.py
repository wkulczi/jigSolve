from flask import request, make_response, send_file
from jigSolve import app, api_logic
import requests


SOLVED_PUZZLES_PATH = 'puzzles_picture.png' 

@app.route("/")
def home():
    return "Welcome!"


@app.route("/upload-puzzle", methods=['POST'])
def post_one_puzzle():
    one_puzzle = request.files['puzzle_piece_picture']
    filename = 'jeden_puzzel.png'
    print(one_puzzle)
    one_puzzle.save(filename)
    print('One puzzle piece image saved')
    return "You send one puzzle!"


@app.route("/upload-puzzles", methods=['POST'])
def post_entire_puzzles():
    entire_puzzles = request.files['entire_puzzles_picture']
    filename = 'cale_puzzle.png'
    entire_puzzles.save(filename)
    print('Entire puzzles image saved')
    return "You send entire puzzles!"
