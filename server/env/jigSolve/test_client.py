import requests
from io import StringIO
import base64


URL = 'http://127.0.0.1:80/'


def post_one_puzzle():
    endpoint = '/upload-puzzle'
    data = {'puzzle': open('puzzle_piece.png', 'rb')}
    response = requests.post(URL+endpoint, files=data)
    print(response.content)

def post_entire_puzzles():
    endpoint = '/upload-puzzles'
    data = {'picture': open('puzzles_picture.png', 'rb')}
    response = requests.post(URL+endpoint, files=data)
    print(response.content)


if __name__ == '__main__':
    post_one_puzzle()
    #post_entire_puzzles()
