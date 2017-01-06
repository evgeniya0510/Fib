#include <stdio.h>
#include <gmp.h>
#include <time.h>
#include <iostream>
#include <omp.h>
#include <fstream>
#include <string>

using namespace std;

void fib(int, const char*);
unsigned long int fib_rec(unsigned long int);
void warp_fib_rec(int);

int main(void)
{
	cout << "############# iter fib 50 raz" << endl;
	fib(50, "iter_fib_50.txt");
	cout << "############# iter fib 100 raz" << endl;
	fib(100, "iter_fib_100.txt");
	cout << "############# recrecur fib 50 raz" << endl;
	warp_fib_rec(40);

	return 0;
}

void fib(int k, const char* name)
{
	ofstream file;
	double stime;
	mpz_t mas[k];
	
	file.open(name);
	for (int i = 0; i <= k; i++) {
		mpz_init (mas[i]);
	}

	mpz_set_ui(mas[0],0);
	mpz_set_ui(mas[1],1);
	
	stime = omp_get_wtime();
	for(int i = 2; i <= k; i++)
	{	
		mpz_add(mas[i], mas[i - 1],mas[i - 2]);
		file << mas[i]
		<< " " 
		<< omp_get_wtime() - stime 
		<< endl;
	}
	for(int val = 0; val <= k; val++) {
		cout <<  mas[val] << endl;
	}

	file.close();
}

void warp_fib_rec(int num)
{
	ofstream file("recur_fib.txt");
	double stime;
	stime = omp_get_wtime();
	cout << stime << endl;
	for(int val = 0; val <= num; val++)
	{
		file << fib_rec(val) 
		<< " " 
		<< omp_get_wtime() - stime
		<< endl;
		cout << fib_rec(val) << endl;		
	}
}

unsigned long int fib_rec(unsigned long int n)
{
	if (n == 0) 
	{
		return 0; 
	}
	else
	{
		if ((n == -1) || (n == 1)) 
		{ 
			return 1; 
		}
		else
		{
			if (n > 0) 
			{ 
				return fib_rec(n - 1) + fib_rec(n - 2); 
			}
			else 
			{ 
				return fib_rec(n + 2) - fib_rec(n + 1); 
			}
		}
	}
}
