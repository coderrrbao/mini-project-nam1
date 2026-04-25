#include <iostream>
#include <stack>
#include <vector>
#include <string.h>
#include <cstdlib>
using namespace std;
string chtos(char a){
    string s;
    s+=a;
    return s;
}
bool ladau(string a){
    return (a=="+"||a=="-"||a=="*"||a=="/");
}
bool lonhon(string a,string b){
    int gt1,gt2;
    gt1=gt2=0;
    if (a=="+"||a=="-") gt1=1;
    if (b=="+"||b=="-") gt2=1;
    if (a=="*"||a=="/") gt1=2;
    if (b=="*"||b=="/") gt2=2;
    return gt1>gt2;
}
bool laso(string s){
    return !(ladau(s)||(s=="(")||s==")");
}
bool isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/');
}
// chat gpt
bool kiemtra(const string& expr) {
    if (expr.empty()) return true; // Biểu thức không được rỗng

    int n = expr.size();
    bool lastWasOperator = true;  // Ban đầu giả sử toán tử để kiểm tra vị trí hợp lệ
    bool lastWasOpenParen = false; // Kiểm tra dấu ngoặc
    stack<char> parentheses;      // Kiểm tra đóng mở ngoặc

    bool inNumber = false; // Đang ở trong một số
    bool dotSeen = false;  // Đã thấy dấu chấm trong số hiện tại

    for (int i = 0; i < n; i++) {
        char c = expr[i];

        if (c == ' ') continue; // Bỏ qua khoảng trắng

        if (isdigit(c)) {
            // Nếu gặp chữ số, đánh dấu đang đọc số và reset các cờ liên quan
            inNumber = true;
            lastWasOperator = false;
            lastWasOpenParen = false;
        }
        else if (c == '.') {
            // Cho phép dấu chấm nếu đang ở số và chưa có dấu chấm nào trong số đó
            if (!inNumber || dotSeen) {
                return false; // Không hợp lệ: hoặc dấu chấm đứng riêng hoặc đã có dấu chấm rồi
            }
            dotSeen = true;
        }
        else if (isOperator(c)) {
            // Khi gặp toán tử, reset trạng thái số
            inNumber = false;
            dotSeen = false;
            
            // Cho phép dấu '-' biểu diễn số âm nếu đứng đầu hoặc sau '('
            if (c == '-' && (i == 0 || expr[i - 1] == '(')) {
                lastWasOperator = false;
            }
            else if (lastWasOperator || i == n - 1) {
                return false; // Không được có toán tử liên tiếp hoặc ở cuối biểu thức
            }
            else {
                lastWasOperator = true;
            }
        }
        else if (c == '(') {
            // Reset trạng thái số khi mở ngoặc
            inNumber = false;
            dotSeen = false;
            
            if (!lastWasOperator && !lastWasOpenParen)
                return false; // Không cho số ngay trước '('
            parentheses.push(c);
            lastWasOperator = true;
            lastWasOpenParen = true;
        }
        else if (c == ')') {
            // Reset trạng thái số khi đóng ngoặc
            inNumber = false;
            dotSeen = false;
            
            if (lastWasOperator || parentheses.empty())
                return false; // Không được có toán tử ngay trước ')' hoặc không có ngoặc mở tương ứng
            parentheses.pop();
            lastWasOperator = false;
        }
        else {
            return false; // Ký tự không hợp lệ
        }
    }

    return !lastWasOperator && parentheses.empty();
}
//
vector <string> tachthanhphan(string s){
    vector <string> tam;
    bool am=false;
    for (int i=0;i<s.size();i++){
        string a;
        if (!laso(chtos(s[i]))){
            if ((i==0&&s[0]=='-')||(i!=0&&s[i]=='-'&&s[i-1]=='(')) am=true;   
            a+=s[i];
            if (!am){
                tam.push_back(a);
            }
        }
        else{
            if (am){
                a+='-';
                am=false;
            } 
            int j=i;
            while (j<s.size()&&laso(chtos(s[j]))||s[i]=='.'){
                a+=s[j];
                j++;
            }
            tam.push_back(a);
            i=j-1;
        }
    }
    return tam;
}
vector <string> chuyendoi(vector <string> s){  
    vector <string> hauto;
    stack <string> dau;   
    for (int i=0;i<s.size();i++){ 
        if (s[i]==" ") continue;
        if (ladau(s[i])){
            if (dau.empty()||lonhon(s[i],dau.top())){
                dau.push(s[i]);
            }
            else{
                while (!dau.empty()&&!lonhon(s[i],dau.top())){
                    hauto.push_back(dau.top());
                    dau.pop();
                }
                dau.push(s[i]);
            }
        }
        else{
            if (s[i]=="("||s[i]==")"){
                if (s[i]=="("){
                    dau.push(s[i]);
                }
                if (s[i]==")"){
                    while (!dau.empty()&&dau.top()!="("){
                        hauto.push_back(dau.top());
                        dau.pop();
                    }
                    dau.pop();
                }
            }
                //chac chan la so
            else{
                hauto.push_back(s[i]);
            }
        }
    }
    while (!dau.empty()){
        hauto.push_back(dau.top());
        dau.pop();
    }
    return hauto;
}
double pheptinh(double a,double b,string dau){
    if (dau=="-") return a-b;
    if (dau=="+") return a+b;
    if (dau=="*") return a*b;
    if (dau=="/") return a/b;
    return 0;
}
double ketqua(string s){
    vector <string> a=tachthanhphan(s);
    vector <string> hauto=chuyendoi(a);
    stack <double> xuli;
    for (int i=0;i<hauto.size();i++){
        if (ladau(hauto[i])){
            double so1=xuli.top();
            xuli.pop();
            double so2=xuli.top();
            xuli.pop();
            double pt=pheptinh(so2,so1,hauto[i]);
            xuli.push(pt);
        }
        else{
            xuli.push(stod(hauto[i]));
        }
    }
    return xuli.top();
}
//chat gpt
void ve(double kq,string pt) {
    string s1,s2;
    s1=pt;
    s2+=to_string(kq);
    // Đảm bảo mỗi dòng tối đa 16 ký tự
    if (s1.length() > 16) s1 = s1.substr(0, 16);
    if (s2.length() > 16) s2 = s2.substr(0, 16);

    string space1(16 - s1.length(), ' '); // Căn chỉnh dòng 1
    string space2(16 - s2.length(), ' '); // Căn chỉnh dòng 2
    cout << " may tinh sieu cap vip " << endl;
    cout << " _____________________ " << endl;
    cout << "|  _________________  |" << endl;
    cout << "| | " << s1 << space1 << "| |" << endl;
    cout << "| | " << s2 << space2 << "| |  " << endl;
    cout << "| |_________________| |" << endl;
    cout << "|  ___ ___ ___   ___  |" << endl;
    cout << "| | 7 | 8 | 9 | | + | |" << endl;
    cout << "| |___|___|___| |___| |" << endl;
    cout << "| | 4 | 5 | 6 | | - | |" << endl;
    cout << "| |___|___|___| |___| |" << endl;
    cout << "| | 1 | 2 | 3 | | x | |" << endl;
    cout << "| |___|___|___| |___| |" << endl;
    cout << "| | . | 0 | = | | / | |" << endl;
    cout << "| |___|___|___| |___| |" << endl;
    cout << "|_____________________|" << endl;
}
//
void giaodien(){
    double kq=0;
    string s;
    while (true){
        if (!kiemtra(s)){
            kq=0;
            s="LOI PHEP TINH";
        }
        system("cls");
        ve(kq,s);
        cout<<"hay nhap phep tinh: "<<endl;
        getline(cin,s);
        if (!kiemtra(s)) continue;
        kq=ketqua(s);
    }
}
int main(){
    giaodien();
    return 0;
}
