from flask import Flask
from os import urandom
from jigSolve.ApiLogic import ApiLogic


app = Flask(__name__)
app.config['SECRET_KEY'] = urandom(24).hex()
api_logic = ApiLogic()

from jigSolve import routes
