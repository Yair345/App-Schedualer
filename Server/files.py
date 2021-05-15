import json
import matplotlib.pyplot as plt


def save_to_file(apps):
    apps_time = {}
    apps_time = json.loads(apps)
    duration_to_send = 0.0

    with open("data.txt", 'r') as data:
        my_file = data.readlines()
    found = False
    for app in apps_time.keys():
        found = False
        for line in my_file:
            if app in line:
                found = True
                application, duration, times = parse_line(line[:-1])
                times += 1
                duration_to_send += (float((apps_time[app]) - float(duration)) / times)
                with open("data.txt", 'a') as f:
                    for new_line in my_file:
                        if app in new_line:
                            continue
                        f.write(new_line)
                    f.write(app + ":" + str(duration_to_send) + "," + times + "\n")
        if not found:
            data.write(app + ":" + apps_time[app] + ",1\n")


def parse_line(line):
    parsed_line = line[:-1].split(":")
    app = parsed_line[0]
    duration = parsed_line[1].split(",")[0]
    hour = int(duration.split(".")[0])
    minute = float(duration.split(".")[1]) / 60
    return app, str(hour) + str(minute), parsed_line[1].split(",")[1]


def open_file():
    with open("data.txt", "r") as f:
        my_file = f.readlines()
    apps = []
    for line in my_file:
        apps.append(parse_line(line))
    return apps


def show_table():
    file = open_file()

    apps, durations, times = [], [], []

    for line in file:
        apps.append(line[0])
        durations.append(float(line[1]))
        times.append(line[2])

    plt.bar(apps, durations)
    plt.xlabel('APPS')
    plt.ylabel('DURATIONS')

    for i in range(len(apps)):
        plt.text(i, durations[i], times[i])

    plt.show()
