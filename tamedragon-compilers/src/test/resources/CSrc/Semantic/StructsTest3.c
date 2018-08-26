/*
	Tests semantics for  self-referential structures
*/

struct a1str{
	int xy;
	double n;
	struct a1str a1;   // NOT OK
};


struct tnode {
	char *word;
	int count;
	struct tnode *left;  // OK
	struct tnode *right; // OK
};

struct xyz_node{
	char *pc;
	double dd;
	struct abc_node *next;
};

struct abc_node{
	char *xt;
	short s;
	struct xyz_node *previous;
};

struct whatever{
	double d;
	struct unknown *unk;
};

void main()
{
	struct tnode *root;
	struct tnode *lt;
	struct tnode *rt;

	struct whatever *w1;	

	struct xyz_node *xyz1, *xyz2;
	struct abc_node *abc1;

	root->left = lt;
	root->right = rt;

	root->left = xyz1;  // NOT OK

	xyz1->next = abc1;
	abc1->previous = xyz1;

	w1->unk = root;
	
}
