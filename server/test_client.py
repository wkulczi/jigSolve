import requests

URL = 'http://127.0.0.1:5000/'


def post_one_puzzle():
    endpoint = 'upload-puzzle'
    data = {'puzzle': open('images_test/puzzle_piece.png', 'rb')}
    response = requests.post(URL + endpoint, files=data)
    print(response.content)


def post_entire_puzzles():
    endpoint = 'upload-puzzles'
    data = {'picture': open('images_test/puzzles_picture.png', 'rb')}
    response = requests.post(URL + endpoint, files=data)
    print(response.content)


def match_puzzle():
    endpoint = 'match-puzzle'
    response = requests.get(URL + endpoint)
    print(response.content)


def home():
    response = requests.get(URL)
    print(response.content)


if __name__ == '__main__':
    #home()
    post_one_puzzle()
    post_entire_puzzles()
    match_puzzle()
