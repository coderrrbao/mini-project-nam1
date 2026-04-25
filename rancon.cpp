#include <iostream>
#include <windows.h>
#include <queue>
#include <conio.h> 
#include <ctime>
#include <cstdlib>
using namespace std;

void dichuyen(int x, int y) {
    COORD coord;
    coord.X = static_cast<SHORT>(y);  // Cột (y) được gán vào coord.X
    coord.Y = static_cast<SHORT>(x);  // Dòng (x) được gán vào coord.Y
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void ancontro() {
    CONSOLE_CURSOR_INFO cursorInfo;
    GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);
    cursorInfo.bVisible = false;
    SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursorInfo);
}
void inmau(int maMau, char kitu) {
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

    // Đổi màu chữ
    SetConsoleTextAttribute(hConsole, maMau);

    // In ký tự
    cout << kitu;

    // Trả màu về mặc định (trắng)
    SetConsoleTextAttribute(hConsole, 7);
}

char laykitu(int x, int y) {
    HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    CHAR_INFO ci;
    COORD coord = { (SHORT)y, (SHORT)x };  // Y là cột, X là dòng
    DWORD read;

    char ch;
    ReadConsoleOutputCharacterA(hConsole, &ch, 1, coord, &read);
    return ch;
}
/// may ham phia tren thi chiu
bool gameover=false;
struct toado{
    int x;
    int y;
};
queue <toado> duoiran;
struct conran{
    toado dau={3,3};
    toado duoi={3,3};     
    int chieudai=2;
    char huong;
    void khoitao(){
        dichuyen(3,3);
        cout<<'0';
        huong='d';
        duoiran.push({3,3});
    }         
};
conran ran;
toado duoimoi(toado duoi){ // cap nhap duoi
    dichuyen(duoi.x,duoi.y);
    cout<<" ";
    duoi=duoiran.front();
    duoiran.pop();
    return duoi;
}
toado daumoi(toado dau,char h){// tra ve toa do ran se toi
    if (h=='a'){
        dau.y--;
    }
    if (h=='s'){
        dau.x++;
    }
    if (h=='d'){
        dau.y++;
    }   
    if (h=='w'){
        dau.x--;
    }
    return dau;
}
char input(){
    if (_kbhit()){
        char a=_getch();
        return (int)a;
    }
    return ran.huong;
}
bool moi=false;
void randichuyen(){
    toado truoc=ran.dau;
    toado dau;
    char a=input();
    if ((a=='a' && ran.huong!='d')||(a=='d'&&ran.huong!='a')||(a=='w'&&ran.huong!='s')||(a=='s'&&ran.huong!='w')){
        ran.huong=a;
    }
    dau = daumoi(ran.dau, ran.huong);
    if (laykitu(dau.x,dau.y)=='#'||laykitu(dau.x,dau.y)=='0'){
        gameover=true;
        return;
    }
    if (laykitu(dau.x,dau.y)!='x'){
        ran.duoi=duoimoi(ran.duoi);
    }
    else{
        ran.chieudai++;
        moi=false;
    }
    ran.dau=dau;
    dichuyen(dau.x,dau.y);
    if (laykitu(dau.x,dau.y)!='x'){
        inmau(12,'O');
        dichuyen(truoc.x,truoc.y);
        inmau(10,'0');
    }
    else{
        inmau(12,'O');
        dichuyen(truoc.x,truoc.y);
        inmau(10,'0');
    }
    duoiran.push(dau);
}
int cot,dong;
void vekhunggame(){
    cot=40;
    dong=25;
    for (int i=1;i<=cot;i++){
        dichuyen(1,i);
        inmau(9,'#');
        dichuyen(dong,i);
        inmau(9,'#');
    }
    for (int i=1;i<=dong;i++){
        dichuyen(i,1);    
        inmau(9,'#');
        dichuyen(i,cot);
        inmau(9,'#');
    }
}
void spam(){
    if (!moi){
        int vitri=cot+rand()%(dong*cot-cot);
        int kitu=laykitu(vitri/cot,vitri%cot);
        while (vitri%cot<=2 ||kitu=='0' || kitu=='#'){
            vitri=cot+rand()%(dong*cot-cot);
            kitu=laykitu(vitri/cot,vitri%cot);
        }
        dichuyen(vitri/cot,vitri%cot);
        inmau(12,'x');
        moi=true;
    }
}
void game(){
    ancontro();
    ran.khoitao();
    vekhunggame();
    while (true){
        spam();
        randichuyen();
        Sleep(120);
        if (gameover){
            dichuyen(13,21);
            cout<<"GAMEOVER";
            return;
        }
        if (ran.chieudai==dong*cot-(dong+cot)/2){
            dichuyen(13,21);
            cout<<"BAN DA THANG";
            return;
        }
        dichuyen(dong+2,1);
        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole, 14);
        cout<<"diem : "<<((ran.chieudai-2)*100);
    }
}
int main(){
    srand(time(0));
    game();
    return 0;
} 