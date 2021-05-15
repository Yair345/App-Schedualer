import socket
import select
import files

LISTEN_PORT = 34679
MAX_MSG_LENGTH = 1024

def main():
    print("Setting up server...")
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('0.0.0.0', LISTEN_PORT))
    server_socket.listen()
    print("Listening for clients...")

    client_sockets = []
    while True:
        rlist, wlist, xlist = select.select([server_socket] + client_sockets, [], [])

        for current_socket in rlist:
            if current_socket is server_socket:
                connection, client_address = current_socket.accept()
                print("New client joined!", client_address)
                client_sockets.append(connection)
            else:
                print("Data from existing client\n")
                data = current_socket.recv(MAX_MSG_LENGTH).decode()
                files.save_to_file(data)
                if data == "":
                    print("Connection closed", )
                    client_sockets.remove(current_socket)
                    current_socket.close()
                else:
                    print(data)


if __name__ == "__main__":
    main()
