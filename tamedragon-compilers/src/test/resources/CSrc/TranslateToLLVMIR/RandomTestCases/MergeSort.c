 #include <stdio.h>
    void merge(int *left,int *right,int *result,int nl,int nr)
    {
        int i=0,j=0,k=0;
        while(nl>0 && nr>0)
        {
            if(left[i] <= right[j])
            {
                result[k] = left[i];
                i++;
                k++;
                nl--;
            }
            else
            {
                result[k] = right[j];
                j++;
                k++;
                nr--;
            }
        }
        while(nl>0)
        {
            result [k] = left[i];
            i++;
            k++;
            nl--;
        }
        while(nr>0)
        {
            result[k] = right[j];
            j++;
            k++;
            nr--;
        }
    }

    int main()
    {
        int a[] = {11,33,95};
        int b[] = {45,82,94};
        int c[6],i;
        printf("\n The first array is: {11,33,95}");
        printf("\n The second array is: {45,82,94}");
        merge(a,b,c,3,3);
        printf("\n The sorted list is: ");

        for(i=0;i<6;i++)
            printf("\n %d",c[i]);
        printf("\n");
    }
