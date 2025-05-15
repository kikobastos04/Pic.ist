import http.server, socketserver, requests, urllib
from http import HTTPStatus

PORT = 8001

class Handler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        if self.path.startswith("/wait-times"):
            # 1) Chama a API da CUF
            target = "https://www.cuf.pt/api/get-waiting-times?locale=pt"
            r = requests.get(target)
            # 2) Responde com JSON + cabeçalhos CORS
            self.send_response(HTTPStatus.OK)
            self.send_header("Content-Type", "application/json")
            self.send_header("Access-Control-Allow-Origin", "*")
            self.end_headers()
            self.wfile.write(r.content)
        else:
            # Serve ficheiros estáticos (Pic.html, etc.)
            super().do_GET()

with socketserver.TCPServer(("", PORT), Handler) as httpd:
    print(f"Servidor a correr em http://localhost:{PORT}")
    httpd.serve_forever()