import socket
import select
import files
import codecs

LISTEN_PORT = 34679
MAX_MSG_LENGTH = 1024


def server():
    print("Setting up server...")  # control
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # server socket
    server_socket.bind(('0.0.0.0', LISTEN_PORT))
    server_socket.listen()
    print("Listening for clients...")  # control

    client_sockets = []
    while True:
        rlist, wlist, xlist = select.select([server_socket] + client_sockets, [], [])  # "select made the server to
        # be for multiple clients

        for current_socket in rlist:  # rlist = readable socket list
            if current_socket is server_socket:
                connection, client_address = current_socket.accept()  # three handshake
                print("New client joined!", client_address)
                client_sockets.append(connection)
            else:
                print("Data from existing client\n")
                data = current_socket.recv(MAX_MSG_LENGTH).decode()
                if data == "":
                    print("Connection closed", )
                    client_sockets.remove(current_socket)
                    current_socket.close()
                else:
                    files.save_to_file(codecs.decode(data, 'rot13'))  # save to the data file


def main():
    good_answer = False
    ans = input("Welcome to my Server!\nWhat do you want to do?\n\t1 - Start server\n\t2 - Show table\n ")  # choice
    # between the server and the graph
    while not good_answer:
        try:
            action = int(ans)
            if action != 1 and action != 2:
                ans = input("Only 1 or 2!\nTry again: ")
                continue
            good_answer = True
        except Exception as e:
            ans = input("Only numbers!\nTry again: ")

    if action == 1:
        server()
    else:
        files.show_table()


if __name__ == "__main__":
    main()
