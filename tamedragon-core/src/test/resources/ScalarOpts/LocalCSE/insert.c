int heap[1000000],heapSize;
void Insert(int element)
{
	int now;
        heapSize++;
        heap[heapSize] = element; /*Insert in the last place*/
        /*Adjust its position*/
        now = heapSize;
        while(heap[now/2] > element)
        {
                heap[now] = heap[now/2];
                now /= 2;
        }
        heap[now] = element;
}
