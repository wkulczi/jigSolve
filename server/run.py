from os import urandom

from routes import app

if __name__ == '__main__':
    app.config['SECRET_KEY'] = urandom(24).hex()

    # for testing
    app.run(debug=True, host='0.0.0.0', port=5000)
    # for deployment
    # app.run(debug=True, host='127.0.0.1', port=80)

    print('Heres where magic happens')




