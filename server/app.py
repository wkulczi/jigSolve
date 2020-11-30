from os import urandom
from flask import Flask


app = Flask(__name__)


if __name__ == '__main__':
    app.config['SECRET_KEY'] = urandom(24).hex()
    app.run(debug=True, host='0.0.0.0', port=5000)
