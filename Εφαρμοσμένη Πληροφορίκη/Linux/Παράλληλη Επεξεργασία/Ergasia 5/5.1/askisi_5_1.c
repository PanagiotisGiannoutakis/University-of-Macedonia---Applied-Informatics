#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main(int argc, char *argv[])
{
	int *x, *y;
   	int i, j, my_num, my_place, numOfThreads, N, chunk;
	double start, stop;
   
	numOfThreads = atoi(argv[1]);
	N = atoi(argv[2]);
	x = malloc(sizeof(int) * N);
	y = malloc(sizeof(int) * N);

	if(numOfThreads == 0) {
		numOfThreads = omp_get_max_threads();
	}

	omp_set_num_threads(numOfThreads);

   	for (i=0; i<N; i++) {
		x[i] = N - i;
	}
	
	start = omp_get_wtime();

	#pragma omp parallel for private(j,i,my_num,my_place)
   	for (j=0; j<N; j++) {
     		my_num = x[j];
     		my_place = 0;
     		for (i=0; i<N; i++) {
			if(my_num > x[i]) {
				my_place++;
			}
		}
     		y[my_place] = my_num;
	}

	stop = omp_get_wtime();

   	for (i=0; i<N; i++) {
		printf("%d\n", y[i]);
	}

	printf("Time elapsed in seconds: %f\n", stop - start );
	
	free(x);
	free(y);	
   	return 0;
}
