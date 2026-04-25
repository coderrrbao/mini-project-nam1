#include <iostream>
#include <windows.h>
#include <conio.h> 
#include <cstdlib>
#include <ctime>
#include <vector>
#include <chrono>
#include <thread> 
using namespace std;
void dichuyen(int x, int y) {
    COORD coord;
    coord.X = static_cast<SHORT>(y);  // Cột (y) được gán vào coord.X
    coord.Y = static_cast<SHORT>(x);  // Dòng (x) được gán vào coord.Y
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void ancontro(){
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
bool gameover=false;
int diem=0;
///
int dong=20;
int cot=70;
///
struct toado{
    int x;
    int y;
    char kitu;
};
struct vatcan{
    toado a[18][4];
    int pt=0;
    int giua;
    int kc=0;
    bool nhandiem=true;
    bool ongsau=true;
    void khoitao(){
        for (int i=2;i<dong;i++){
            for (int j=cot+1;j<=cot+4;j++){
                int x=pt/4;
                int y=pt%4;
                a[x][y]={i,j};
                a[x][y].kitu='*';
                pt++;
            }
        }
        giua=(dong-1)/2;
        int arr[10]={giua-3,giua-2,giua,giua-1,giua+1,giua+2,giua+3,giua+4,giua+5,giua-5};
        giua=arr[rand()%10];
        for (int i=giua-3;i<=giua+3;i++){
            for (int j=0;j<4;j++){
                a[i][j].kitu=' ';
            }
        }
    }
    void capnhap(){
        for (int i=0;i<18;i++){
            for (int j=0;j<4;j++){
                a[i][j].y--;
            }
        }
        for (int i=0;i<18;i++){
            dichuyen(a[i][3].x,a[i][3].y+1);
            if (laykitu(a[i][3].x,a[i][3].y+1)!='#'){
                cout<<' ';
            }
            
        }
        kc++;
    }
    void xuat(){
        for (int i=0;i<18;i++){
            for (int j=0;j<4;j++){
                if (a[i][j].y>=2 && a[i][j].y<=69){
                    dichuyen(a[i][j].x,a[i][j].y);
                    char kitu=laykitu(a[i][j].x,a[i][j].y);
                    if (kitu!='#' && kitu!=' '&& kitu!='*' && nhandiem){
                        diem++;
                        nhandiem=false;
                    }
                    if (kitu!=' ' && kitu!='#') continue;
                    inmau(10,a[i][j].kitu);
                }
            }
        }
    }
};
char input(){
    if (_kbhit()){
        char a=_getch();
        if (a==' '){
            return a;
        }
    }
    return '.';
}
vector <vatcan> cacong;
void khunggame(){
    for (int i=1;i<=cot;i++){
        dichuyen(1,i);
        inmau(13,'#');
        this_thread::sleep_for(chrono::milliseconds(1));
        dichuyen(dong,i);
        inmau(13,'#');
        this_thread::sleep_for(chrono::milliseconds(1));
    }
    for (int i=1;i<=dong;i++){//10 xanh la   // 11 la xanh lam   /// 13 mau hong
        dichuyen(i,1);
        inmau(13,'#');
        this_thread::sleep_for(chrono::milliseconds(1));
        dichuyen(i,cot);
        inmau(13,'#');
        this_thread::sleep_for(chrono::milliseconds(1));
    }
}
struct bird{
    toado a[2][4];
    void khoitao(){
        int pt=0;
        for (int i=9;i<=10;i++){
            for (int j=15;j<=18;j++){
                int x=pt/4;
                int y=pt%4;
                a[x][y]={i,j};
                pt++;
            }
        }
        a[0][0].kitu=' ';a[0][1].kitu=' ';a[0][2].kitu='0';a[0][3].kitu='>';
        a[1][0].kitu='(';a[1][1].kitu='0';a[1][2].kitu='0';a[1][3].kitu=')';
    }
    void xuat(){
        for (int i=0;i<2;i++){
            for (int j=0;j<4;j++){
                dichuyen(a[i][j].x,a[i][j].y);
                if (laykitu(a[i][j].x,a[i][j].y)=='#' || laykitu(a[i][j].x,a[i][j].y)=='*') gameover=true;
                inmau(11,a[i][j].kitu);
            }
        }
    }
    void capnhap(char lenh){
        if (lenh!=' '){
            for (int i=0;i<2;i++){
                for (int j=0;j<4;j++){
                    dichuyen(a[i][j].x,a[i][j].y);
                    cout<<" ";
                    a[i][j].x++;
                }
            }
        }
        else{
            for (int i=0;i<2;i++){
                for (int j=0;j<4;j++){
                    dichuyen(a[i][j].x,a[i][j].y);
                    cout<<" ";
                    a[i][j].x-=3;
                }
            }
        }
        xuat();
    }
};
int main(){
   srand(time(0));
   ancontro();
   khunggame();
    bird chim;
    chim.khoitao();
    chim.xuat();
    vatcan ong;
   ong.khoitao();
   ong.xuat();
   cacong.push_back(ong);
    while (true){
        for (int i=0;i<cacong.size();i++){
            cacong[i].capnhap();
            if (cacong[i].kc>=20 && cacong[i].ongsau){
                vatcan a;
                a.khoitao();
                cacong.push_back(a);
                cacong[i].ongsau=false;
            }
            cacong[i].xuat();
            if (cacong[i].a[0][3].y < 1){
                cacong.erase(cacong.begin() + i);
                i--;
            }
        }
        chim.capnhap(input());
        if (gameover) break;
        dichuyen(23,1);
        cout<<"diem : "<<diem;
        Sleep(150);
    }
    dichuyen(23,1);
    cout<<" GAME OVER";
    return 0;
}