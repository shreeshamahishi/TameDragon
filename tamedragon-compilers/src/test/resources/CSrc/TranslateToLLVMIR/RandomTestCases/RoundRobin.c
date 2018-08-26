#include<stdio.h>
#include<stdlib.h>
int ttime,i,j,temp;
rrobin(int pname[],int btime[],int n)
{
	int tslice;
	j=0;
	printf("\n\t ROUND ROBIN SCHEDULING \n\n");
	printf("Enter the time slice:\n");
	scanf("%d",&tslice);
	printf("PROCESS NAME \t REMAINING TIME\t TOTAL TIME");


	while(j<n)
	{
		for(i=0;i<n;i++)
		{
			if(btime[i]>0)
			{
				if(btime[i]>=tslice)
				{
					ttime+=tslice;
					btime[i]=btime[i]-tslice;
					printf("\n%d\t\t %d \t\t %d",pname[i],btime[i],ttime);
					if(btime[i]==0)
						j++;
				}
				else
				{
					ttime+=btime[i];
					btime[i]=0;
					printf("\n%d\t\t %d \t\t %d",pname[i],btime[i],ttime);
				}
			}
		}
	}
}
main()
{
	int pname[10],btime[10],pname2[10],btime2[10];
	int n,x,z;
	printf("Enter the no. of process:");
	scanf("%d",&n);
	printf("Enter the process name and burst time for the process\n");
	for(i=0;i<n;i++)
	{
		printf("Enter the process name:");
		scanf("%d",&pname2[i]);
		printf("Enter burst time for the process %d:",pname2[i]);
		scanf("%d",&btime2[i]);
	}
	printf("PROCESS NAME \t\t BURST TIME\n");
	for(i=0;i<n;i++)
		printf("%d\t\t\t %d\n",pname2[i],btime2[i]);
	z=1;
	while(z==1)
	{
		ttime=0;
		for(i=0;i<n;i++)
		{
			pname[i]=pname2[i];
			btime[i]=btime2[i];
		}

		printf ("PRESS 1.ROUND ROBIN 2.EXIT\n");
		scanf("%d",&x);
		switch(x)
		{
		case 1:
			rrobin(pname,btime,n);
			break;
		case 2:
			exit(0);
			break;
		default:
			printf("Invalid option");
			break;
		}
		printf("\n\n If you want to continue press 1:");
		scanf("%d",&z);
	}
}
