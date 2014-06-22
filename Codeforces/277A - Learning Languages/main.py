import fileinput


EMPLOYEE, LANGUAGE = range(2)


def main():
    input = fileinput.input()
    n, m = input.next().split()
    m = int(m)
    n = int(n)

    graph = init_graph(input)
    employees = set()
    berdollars = 0

    if not sum(len(v) for v in graph.values()):
        print n
    else:
        for i in range(1, n + 1):
            visited = depth_first_traversal(graph, (EMPLOYEE, i))
            visited = {i for i in visited if i[0] == EMPLOYEE}
            if visited - employees:
                berdollars += 1
                employees |= visited

        print berdollars - 1


def init_graph(input):
    graph = {}

    for line in input:
        n = (EMPLOYEE, input.lineno() - 1)
        graph[n] = set()

        for lang_id in line.split()[1:]:
            lang_node = (LANGUAGE, int(lang_id))

            if lang_node not in graph:
                graph[lang_node] = set()

            graph[lang_node].add(n)
            graph[n].add(lang_node)

    return graph


def depth_first_traversal(graph, start):
    visited = set()
    frontier = [start]
    visited.add(start)

    while frontier:
        current = frontier.pop()
        for neighbor in graph[current]:
            if neighbor not in visited:
                frontier.append(neighbor)
                visited.add(neighbor)

    return visited


if __name__ == '__main__':
    main()
