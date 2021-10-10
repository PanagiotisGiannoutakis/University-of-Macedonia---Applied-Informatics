# include <stdlib.h>
# include <stdio.h>
# include <string.h>
# include <math.h>
# include <omp.h>

double f( double );
double f( double a )
{
    return (4.0 / (1.0 + a*a));
}

int main( int argc, char *argv[])
{
	int done = 0, n, i, numOfThreads;
	double PI25DT = 3.141592653589793238462643;
	double pi, h, sum, x, start, stop;
   
	numOfThreads = atoi(argv[1]);
	n = atoi(argv[2]);

	if(numOfThreads == 0) {
		numOfThreads = omp_get_max_threads();
	}

	omp_set_num_threads(numOfThreads);

        start = omp_get_wtime();
	
	pi = 0.0;
	h  = 1.0 / (double) n;
	sum = 0.0;

	#pragma omp parallel private(i,x)
	{
		#pragma omp for
		for (i = 0; i <= n; i++) {
		        x = h * ((double)i - 0.5);
			#pragma omp critical
			{
		        	sum += f(x);
				//printf("I am thread %d with i: %d and sum: %f\n", omp_get_thread_num(), i, sum);
			}
		}
	}
	printf("Total sum: %f\n", sum);	

	pi = h * sum;

	stop = omp_get_wtime();
        printf("pi is approximately %.16f, Error is %.16f\n", pi, fabs(pi - PI25DT));
	printf("Time elapsed in seconds with critical: %f\n", stop - start );

	// Execute program with reduction variable
	start = omp_get_wtime();

	pi = 0.0;
	h  = 1.0 / (double) n;
	sum = 0.0;
	#pragma omp parallel private(i) reduction(+:sum)
	{
		#pragma omp for
		for (i = 0; i <= n; i++) {
		        x = h * ((double)i - 0.5);
		        sum += f(x);
			//printf("I am thread %d with i: %d and sum: %f\n", omp_get_thread_num(), i, sum);
		}
	}
	printf("Total sum: %f\n", sum);

	pi = h * sum;

	stop = omp_get_wtime();
        printf("pi is approximately %.16f, Error is %.16f\n", pi, fabs(pi - PI25DT));
	printf("Time elapsed in seconds with reduction: %f\n", stop - start );
	
    	return 0;
}

            
