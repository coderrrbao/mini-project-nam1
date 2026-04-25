#include <iostream>
using namespace std;
struct may{
    int a[6];
    int n=0;
    int may;
    int tong=0;
    void danhso(int x){
        may=x;
    }
};

void chiaviec(may maytinh[], int congviec[],int congviecindex[],int cvthu){
    if (cvthu>=6) return;
    for (int i=0;i<3;i++){
        int min=i;
        for (int j=i+1;j<3;j++){
            if (maytinh[j].tong<maytinh[min].tong){
                min=j;
            }
        }
        if (cvthu<6){
            maytinh[min].tong+=congviec[cvthu];
            maytinh[min].a[maytinh[min].n++]=congviecindex[cvthu];
            cvthu++;
        }
        else{
            break;
        }
    }
    chiaviec(maytinh,congviec,congviecindex,cvthu);
}
void sapxep(int congviec[],int congviecindex[]){
    for (int i=0;i<6;i++){
        int min=i;
        for (int j=i+1;j<6;j++){
            if (congviec[j]<congviec[min]){
                min=j;
            }
        }
        swap(congviec[i],congviec[min]);
        swap(congviecindex[i],congviecindex[min]);
    }
}
int main(){
    int congviec[6];
    int congviecindex[6];
    for (int i=0;i<6;i++){
        cin>>congviec[i];
        congviecindex[i]=i+1;
    }
    may maytinh[3];
    maytinh[0].danhso(1);
    maytinh[1].danhso(2);
    maytinh[2].danhso(3);
    sapxep(congviec,congviecindex);
    chiaviec(maytinh,congviec,congviecindex,0);
    cout<<"may tinh so "<<maytinh[0].may<<"duoc giao cho cac cong viec ";
    for (int i=0;i<maytinh[0].n;i++){
        cout<<maytinh[0].a[i]<<" ";
    }
    cout<<endl;
    cout<<"may tinh so "<<maytinh[1].may<<"duoc giao cho cac cong viec ";
    for (int i=0;i<maytinh[1].n;i++){
        cout<<maytinh[1].a[i]<<" ";
    }
    cout<<endl;
    cout<<"may tinh so "<<maytinh[2].may<<"duoc giao cho cac cong viec ";
    for (int i=0;i<maytinh[2].n;i++){
        cout<<maytinh[2].a[i]<<" ";
    }
    cout<<endl;
}