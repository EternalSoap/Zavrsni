#include<fstream>
#include<iostream>
#include<cstdlib>
#include<sstream>
using namespace std;
int main(){
	ostringstream oss;
	string ime;
	string broj;
	string e = ".obj";
	for(int i=0;i<=8;i++){
		oss.str("");
		oss<<i;
		broj = oss.str();
		ime = "data"+broj+e;
		cout<<ime<<endl;
		fstream d(ime.c_str(),ios::out);
	}
}
