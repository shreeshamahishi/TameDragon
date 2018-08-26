
int glb1 = 50, glb2 = 200;

int foo()
{
	int m, n, result;
	
	m = glb1;
	glb2 = 25;
	n = glb1;
	
	result = m + n;
	
	return result;
}