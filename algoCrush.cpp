#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
bool compare(const int *a,const int *b)
{
    if(a[0]<b[0])
        return true;
    if(a[0]==b[0])
    {
        if(a[2]<b[2])
            return true;
    }
    return false;
}
int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int n,m;
    cin>>n>>m;
    long arr1=0;
    int arr2[m][3];
    for(int i=0;i<m;i++)
    {
        cin>>arr2[i][0];
        cin>>arr2[i][1];
        cin>>arr2[i][2];
        //cout<<arr2[i][2];
        arr2[i][0]--;
        arr2[i][1]--;
    }
    long m1=0;
    sort(arr2,arr2+m,compare);
    cout<<m1;
    return 0;
}

