from jigSolve import app


if __name__ == '__main__':
    # for testing
    app.run(debug=True, host='127.0.0.1', port=80)
    # for deployment
    # app.run(debug=True, host='0.0.0.0', port=80)