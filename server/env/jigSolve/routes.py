from flask import request, make_response
from jigSolve import app, api_logic


@app.route("/")
def home():
    return "Welcome!"


@app.route("/puzzle", methods=['POST'])
def post_one_puzzle():
    return "You send one puzzle!"


@app.route("/puzzles", methods=['POST'])
def post_entire_puzzles():
    return "Here comes your pic!"
