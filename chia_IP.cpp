#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <climits>
using namespace std;
struct IP{
    int add[4];    // vd 192 168 1 0
    int sub[4];  // vd 255 255 255 0 
    int subgon; //  /xx
};
struct phong{
    IP ip;
    int soluong;  
    bool mang=false;
};
int thapphan(string s){ // chuyen nhi phan sang thap phan
    int n=s.size();
    int sum=0;
    int mu=1;
    for (int i=n-1;i>=0;i--){
        sum+=(s[i]-'0')*mu;
        mu*=2;
    }
    return sum;
}
void submask(IP &ip){  // cap nhap submask khi bt submask rut gon
    string a[4];
    int net=ip.subgon;
    for (int i=0;i<4;i++){
        string octec;
        for (int j=0;j<8&&net>0;j++){
            octec.push_back('1');
            net--;
        }
        while (octec.size()<8){
            octec.push_back('0');
        }
        a[i]=octec;
    }
    for (int i=0;i<4;i++){
        ip.sub[i]=thapphan(a[i]);
    }
} 
IP taoip(string s,string sub){  // tao 1 ip dua tren ip add va submask rut gon
    IP ip;
    stringstream ss(s);
    string octet;
    int n=0;
    while (getline(ss,octet,'.')){
        ip.add[n++]=stoi(octet);
    }
    sub.erase(0,1);
    ip.subgon=stoi(sub);
    submask(ip);
    return ip;
}
void xuat(IP ip){ // xuat ip
    for (int i=0;i<4;i++){
        cout<<ip.add[i]<<(i!=3?".":"");
    }
    cout<<" /"<<ip.subgon<<endl;
    for (int i=0;i<4;i++){
        cout<<ip.sub[i]<<(i!=3?".":"");
    }
}
int mangphuhop(int soluong){ // so bit net toi thieu de chia vua du
    int subgon=32;
    while (pow(2,32-subgon)-2<soluong){
        subgon--;
    }
    return subgon;
}
IP congip(IP ip,int octec,int so){ // cong ip vs 1 so
    ip.add[octec]+=so;
    for (int i=octec-1;i>=0;i--){
        if (ip.add[i+1]>=255){
            ip.add[i]+=ip.add[i+1]/256;
            ip.add[i+1]=ip.add[i+1]%256;
        }
    }
    return ip;
}
int octeccobit0(IP ip){ // octet dau tien co bit 0
    for (int i=0;i<4;i++){
        if (ip.sub[i]!=255) return i+1;
    }
    return -1;
}
string chuanhoaip(IP ip){ // lay ip tu mang ve string
    string s;
    for (int i=0;i<4;i++){
        s+=to_string(ip.add[i])+".";
    }
    s.pop_back();
    return s;
}
vector <vector <IP>> dayip; // chua day ip tu theo thu tu mang lon -> nho
void chiaip(IP ip,int sub,phong p[],int n){
    vector <IP> hangdoi;// tao 1 day ip voi submask rut gon la : /sub
    string s="/"+to_string(sub);
    IP tam=taoip(chuanhoaip(ip),s);
    p[n].ip=tam;
    p[n].mang=true;
    //////da cap phat xong///////
    if (sub==ip.subgon){ /// neu cung sub thi ko can chia
        return;
    }
    int o0=octeccobit0(tam)-1;
    int buocnhay=256-tam.sub[o0];
    int somangconlai=pow(2,sub-ip.subgon)-1;///them cac mang con lai vao trong day
    for (int i=1;i<=somangconlai;i++){
        tam=congip(tam,o0,buocnhay);
        hangdoi.push_back(tam);
    }
    dayip.push_back(hangdoi);
}
void chiaIpphong(phong p[],int n,IP ip){
    dayip.push_back({ip});
    for (int i=0;i<n;i++){
        int maxhost=INT_MIN;
        int vt=-1;
        for (int j=0;j<n;j++){
            if (maxhost<p[j].soluong && !p[j].mang){
                maxhost=p[j].soluong;
                vt=j; // chon ra phong nhieu host nhat chua co mang
            }
        }
        if (p[vt].soluong==0){
            p[vt].ip=taoip("0.0.0.0","/0");
            p[vt].mang=true;
            continue;
        }
        int m=mangphuhop(p[vt].soluong);
        int subvitri=dayip.back()[0].subgon; // 2 truong hop : = hoac < hon m
        int cuoi=dayip.size()-1;
        if (dayip.empty() || dayip[cuoi][0].subgon>m){ //het ip hoac ko chia dc nua
            cout<<"khong chia duoc bruh";
            return;
        }
        chiaip(dayip[cuoi][0],m,p,vt);
        dayip[cuoi].erase(dayip[cuoi].begin()); //xoa ip da cap phat
        if (dayip[cuoi].empty()) dayip.erase(dayip.begin()+cuoi); //xoa 1 day mang neu da cap phat het mang
    }
    cout<<endl;
    for (int i=0;i<n;i++){
        cout<<"phong "<<i+1<<":"<<endl;
        xuat(p[i].ip);
        cout<<endl;
    }
}
void somangkhadung(vector <IP>&hangdoi){
    cout<<"so mang con lai sau khi chia :"<<endl;
    for (int i=0;i<hangdoi.size();i++){
        xuat(hangdoi[i]);
        cout<<endl;
    }
}
int main(){
    cout<<"nhap IP ban dau : ";
    string s;getline(cin,s);
    int so;
    cout<<"/";
    cin>>so;
    IP ip=taoip(s,"/"+to_string(so));
    int n;
    phong p[100];
    cout<<"hay nhap so phong can chia : ";
    cin>>n;
    cout<<"nhap lan luot so luong host cua moi phong :"<<endl;
    for (int i=0;i<n;i++){
        cin>>p[i].soluong;
    }
    chiaIpphong(p,n,ip);
    return 0;
}