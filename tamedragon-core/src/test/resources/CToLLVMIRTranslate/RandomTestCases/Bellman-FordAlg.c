#include<stdio.h>
/* maxVertices represents maximum number of vertices that can be present in the graph. */
#define maxVertices   100
#define INF 123456789
/* Input Format: Graph is directed and weighted. First two integers must be number of vertices and edges
   which must be followed by pairs of vertices which has an edge between them.
 */
void BellmanFord(int graph[][maxVertices],int cost[][maxVertices],int size[],int source,int vertices)
{
        int distance[vertices];
        int iter,jter,from,to;
        for(iter=0;iter<vertices;iter++)
        {
                distance[iter] = INF;
        }
        distance[source] = 0;
        /* We have to repeatedly update the distance |V|-1 times where |V| represents
           number of vertices */
        for(iter=0;iter<vertices-1;iter++)
        {
                for(from=0;from<vertices;from++)
                {
                        for(jter=0;jter<size[from];jter++)
                        {
                                to = graph[from][jter];
                                if(distance[from] + cost[from][jter] < distance[to])
                                {
                                        distance[to] = distance[from] + cost[from][jter];
                                }
                        }
                }
        }
        for(iter=0;iter<vertices;iter++)
        {
                printf("The shortest distance to %d is %d\n",iter,distance[iter]);
        }


}
int main()
{
        int graph[maxVertices][maxVertices],size[maxVertices]={0},visited[maxVertices]={0};
        int cost[maxVertices][maxVertices];
        int vertices,edges,iter,jter;
        int vertex1,vertex2,weight;
        int source;
        /* vertices represent number of vertices and edges represent number of edges in the graph. */
        printf("Enter number of vertices ");
        scanf("%d",&vertices);
        printf("\nEnter number of edges ");
        scanf("%d",&edges);
        /* Here graph[i][j] represent the weight of edge joining i and j */
        for(iter=0;iter<edges;iter++)
        {
                printf("\nEnter vertex1 ");
        	    scanf("%d",&vertex1);
        	    printf("\nEnter vertex2 ");
                scanf("%d",&vertex2);
                printf("\nEnter the weight ");
                scanf("%d",&weight);
                graph[vertex1][size[vertex1]] = vertex2;
                cost[vertex1][size[vertex1]] = weight;
                size[vertex1]++;
        }
        printf("\nEnter Source ");
        scanf("%d",&source);
        BellmanFord(graph,cost,size,source,vertices);
        return 0;
}
