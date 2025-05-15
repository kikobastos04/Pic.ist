from flask import Flask, jsonify
from flask_cors import CORS
import requests

app = Flask(__name__)
CORS(app)  # adiciona Access-Control-Allow-Origin: *

@app.route("/wait-times")
def wait_times():
    # busca na CUF
    r = requests.get("https://www.cuf.pt/api/get-waiting-times?locale=pt")
    return jsonify(r.json())

if __name__ == "__main__":
    # roda em http://localhost:5000
    app.run(port=5000)
