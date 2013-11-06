import fileinput


def read_graph():
    i = fileinput.input()
    edges = set()
    nodes = set()
    for line in i:
        if i.isfirstline():
            continue
        else:
            head, tail, weigth = line.split()
            head, tail, weigth = int(head), int(tail), int(weigth)
            edges.add((head, tail, weigth))
            edges.add((tail, head, weigth))
            nodes.update((head, tail))

    return (nodes, edges)


def prim(graph, start):
    mst = set()
    visited = {start}
    nodes, edges = graph

    while visited != nodes:
        possible_edges = (e for e in edges if e[0] in visited
                          and e[1] not in visited)
        cheapest_edge = min(possible_edges, key=lambda x: x[2])
        mst.add(cheapest_edge)
        visited.add(cheapest_edge[1])

    return mst


def main():
    graph = read_graph()
    mst = prim(graph, 1)
    print sum(i[2] for i in mst)

if __name__ == '__main__':
    main()
