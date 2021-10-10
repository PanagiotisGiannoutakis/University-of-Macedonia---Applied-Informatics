#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main(int argc, char *argv[])
{
   double **a,**b,**c, **reversec, sum, start, stop;
   int i, j, k, m, n, q, numOfThreads;

   	/* Get matrix dimensions */
   	printf("Please give m, n and q: ");
   	scanf("%d %d %d", &m, &n, &q);
   	printf("\n");

	/* Get number of threads */
   	printf("Please number of threads: ");
   	scanf("%d", &numOfThreads);
   	printf("\n");

   	/* Memory allocation */
   	a=(double **)malloc(m*sizeof(double *));
   	for (i=0; i<m; i++) {
   		a[i]=(double *)malloc(n*sizeof(double));
	}

   	b=(double **)malloc(m*sizeof(double *));
   	for (i=0; i<m; i++) {
        	b[i]=(double *)malloc(q*sizeof(double));
	}

   	c=(double **)malloc(q*sizeof(double *));
   	for (i=0; i<q; i++) {
   		c[i]=(double *)malloc(n*sizeof(double));
	}

	reversec=(double **)malloc(n*sizeof(double *));
   	for (i=0; i<n; i++) {
   		reversec[i]=(double *)malloc(q*sizeof(double));
	}

   	/* Some initialization */
   	for (i=0; i<m; i++) {
      		for (j=0; j<q; j++) {
         		b[i][j] = i+j;
		}
	}

   	for (i=0; i<q; i++) {
      		for (j=0; j<n; j++) {
        		c[i][j] = i+j+1;
		}
	}
	

	for(i=0; i<n; i++) {
		for(j=0; j<q; j++) {
			reversec[i][j] = c[j][i];
		}
	}

	if(numOfThreads == 0) {
		numOfThreads = omp_get_max_threads();
	}

	omp_set_num_threads(numOfThreads);

        start = omp_get_wtime();

   	/* Parallel part */
   	#pragma omp parallel for shared(m,n,q, a,b,c) private(i,j,k, sum)
   	for (i=0; i<m; i++) {
	  	for (j=0; j<n; j++) {
			sum = 0.0;
			for (k=0; k<q; k++) {
				sum += b[i][k]*reversec[j][k];
				//printf("This is thread %d and i=%d j=%d k=%d\n", omp_get_thread_num(), i, j, k);
			}
			a[i][j] = sum;
	  	}		
   	}
   
	stop = omp_get_wtime();

   	/* Print result */
   	/*for (i=0; i<m; i++) {
	   	for (j=0; j<n; j++) {
		    	printf ("%f ", a[i][j]);
		}
	   	printf("\n");
   	}*/

	printf("Time elapsed in seconds: %f\n", stop - start ); 

   	for (i=0; i<m; i++) {
   		free(a[i]);
	}
   	free(a);
   	for (i=0; i<m; i++) {
        	free (b[i]);
	}
   	free(b);
   	for (i=0; i<q; i++) {
   		free (c[i]);
	}
   	free(c);

   	return(0);
}



