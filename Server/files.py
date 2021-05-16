import json
import matplotlib.pyplot as plt


def save_to_file(apps):
    """
    add the new data from the user
    :param apps: json of apps from the user
    """
    apps_time = {}
    apps_time = json.loads(apps)
    duration_to_send = 0.0

    with open("data.txt", 'r') as data:  # open the file
        my_file = data.readlines()
    found = False
    for app in apps_time.keys():  # app from the user
        found = False
        for line in my_file:
            if app in line:  # check for every line if the app is in it
                found = True
                application, duration, times = parse_line(line[:-1])  # cut '\n'
                times += 1
                duration_to_send += (float((apps_time[app]) - float(duration)) / times)  # add to the average
                with open("data.txt", 'a') as f:
                    for new_line in my_file:  # rewrite the file
                        if app in new_line:
                            continue
                        f.write(new_line)
                    f.write(app + ":" + str(duration_to_send) + "," + times + "\n")
        if not found:
            with open("data.txt", 'a') as f:  # add a new app
                f.write(app + ":" + apps_time[app] + ",1\n")


def parse_line(line):
    """
    get a line from the file and get the  information
    :param line: line from my file
    :return: tuple of (app name, average time, how mach users block this application)
    """
    parsed_line = line[:-1].split(":")
    app = parsed_line[0]
    duration = parsed_line[1].split(",")[0]
    hour = int(duration.split(".")[0])
    minute = float(duration.split(".")[1]) / 60
    return app, str(hour) + str(minute), parsed_line[1].split(",")[1]


def open_file():
    """
    Extract information from the file
    :return: list of lines
    """
    with open("data.txt", "r") as f:
        my_file = f.readlines()
    apps = []
    for line in my_file:
        apps.append(parse_line(line))
    return apps


def show_table():
    """
    shows the bar graph
    """
    file = open_file()

    apps, durations, times = [], [], []

    for line in file:  # create lists of name apps, durations and times
        apps.append(line[0])
        durations.append(float(line[1]))
        times.append(line[2])

    plt.bar(apps, durations)  # create graph
    plt.xlabel('APPS')
    plt.ylabel('DURATIONS')

    for i in range(len(apps)):
        plt.text(i, durations[i], times[i])  # add times on top of all column

    plt.show()  # shows the graph
