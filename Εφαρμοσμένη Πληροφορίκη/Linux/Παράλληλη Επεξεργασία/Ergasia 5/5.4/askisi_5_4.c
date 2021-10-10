#include <stdio.h> 
#include <stdlib.h>
#include <omp.h> 
#define N 128
#define base 0

int main (int argc, char *argv[]) {
	
	FILE *pFile;
	long file_size;
	char * buffer;
	char * filename;
	size_t result;
	int i, j, freq[N], numOfThreads;
	double start, stop;
	
	numOfThreads = atoi(argv[1]);

	if(numOfThreads == 0) {
		numOfThreads = omp_get_max_threads();
	}

	omp_set_num_threads(numOfThreads);


	filename = "world192.txt";
	pFile = fopen ( filename , "rb" );
	if (pFile==NULL) {
		printf ("File error\n"); exit (1);
	}

	// obtain file size:
	fseek (pFile , 0 , SEEK_END);
	file_size = ftell (pFile);
	rewind (pFile);
	printf("file size is %ld\n", file_size);
	
	// allocate memory to contain the file:
	buffer = (char*) malloc (sizeof(char)*file_size);
	if (buffer == NULL) {
		printf ("Memory error\n"); exit (2);
	}

	// copy the file into the buffer:
	result = fread (buffer,1,file_size,pFile);
	if (result != file_size) {
		printf ("Reading error\n"); exit (3);
	} 
	
	for (j=0; j<N; j++){
		freq[j]=0;
	}

	start = omp_get_wtime();

	#pragma omp parallel for private(i)
	for (i=0; i<file_size; i++){
		#pragma omp critical
		freq[buffer[i] - base]++;
	}		

	stop = omp_get_wtime();

	for (j=0; j<N; j++){
		printf("%d = %d\n", j+base, freq[j]);
	}	

	printf("Time elapsed in seconds: %f\n", stop - start );

	fclose (pFile);
	free (buffer);

	return 0;
}
