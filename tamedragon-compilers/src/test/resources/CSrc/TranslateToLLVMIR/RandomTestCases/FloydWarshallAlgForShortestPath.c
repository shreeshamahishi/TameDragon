#include<stdio.h>
/* maxVertices represents maximum number of vertices that can be present in the graph. */
#define maxVertices   100
#define INF 123456789
/* Input Format: Graph is directed and weighted. First two integers must be number of vertces and edges
   which must be followed by pairs of vertices which has an edge between them.
 */
int min(int a,int b)
{
	int ret = (a < b)? a : b;
	return ret;
}
void init(int distance[maxVertices][maxVertices])
{
	int iter,jter;
	for(iter=0;iter<maxVertices;iter++)
	{
		for(jter=0;jter<maxVertices;jter++)
		{
			if(iter==jter)
			{
				distance[iter][jter] = 0;
			}
			else
			{
				distance[iter][jter] = INF;
			}
		}
	}
}
void FloydWarshall(int distance[maxVertices][maxVertices],int vertices)
{
	int from,to,via;
	for(from=0;from<vertices;from++)
	{
		for(to=0;to<vertices;to++)
		{
			for(via=0;via<vertices;via++)
			{
				distance[from][to] = min(distance[from][to],
						distance[from][via]+distance[via][to]);
			}
		}
	}
}
int main()
{
	int graph[maxVertices][maxVertices],size[maxVertices]={0},visited[maxVertices]={0};
	int distance[maxVertices][maxVertices];
	int vertices,edges,iter,jter;
	int vertex1,vertex2,weight;
	/* vertices represent number of vertices and edges represent number of edges in the graph. */
	printf("Enter number of vertices ");
	scanf("%d",&vertices);
	printf("\nEnter number of edges ");
	scanf("%d",&edges);
	/*initialize distance between all pairs as infinity*/
	init(distance);
	/* Here graph[i][j] represent the weight of edge joining i and j */
	for(iter=0;iter<edges;iter++)
	{
		printf("\nEnter vertex1 ");
		scanf("%d",&vertex1);
		printf("\nEnter vertex2 ");
		scanf("%d",&vertex2);
		printf("\nEnter the weight ");
		scanf("%d",&weight);
		graph[vertex1][vertex2] = weight;
		distance[vertex1][vertex2] = weight;
	}
	FloydWarshall(distance,vertices);
	for(iter=0;iter<vertices;iter++)
	{
		for(jter=0;jter<vertices;jter++)
		{
			if(distance[iter][jter]>=INF)
			{
				printf("The shortest distance between %d and %d is Infinity\n",iter,jter);
			}
			else
			{
				printf("The shortest distance between %d and %d is %d\n",iter,jter,distance[iter][jter]);
			}
		}
	}

	return 0;
}
