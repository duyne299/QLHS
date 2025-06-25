import os
import sqlite3

from tkinter import *
import icon

from Login import DataTableFrame
from Class import DataTableFrame_LopHoc
from Subject import DataTableFrame_MonHoc
from Student import DataTableFrame_Hocsinh
from Diem import FrameDiem

windown = Tk()
windown.title("Quản lí học sinh")
windown.geometry("1042x565")
# Frame menu
framemenu = Frame(width=181, height=571)
framemenu.config(background='lightblue')
framemenu.pack(padx=0, pady=0)
framemenu.place(x=0, y=0)
# Các icon button trong menu

icon_student = PhotoImage(file="icon/student.png")
icon_subject = PhotoImage(file="icon/subject.png")
icon_class = PhotoImage(file="icon/class.png")
icon_score = PhotoImage(file="icon/score.png")
icon_account = PhotoImage(file="icon/account.png")
icon_logout = PhotoImage(file="icon/dangxuat.png")


# frame_avatar = Frame(padx=0, pady=0, width=181, height=120,image=avt)
# frame_avatar.place(x=0, y=0)


# lb_avt = Label(framemenu, text=f"Welcome to {}", background='lightblue', font=("Arial", 11, ""))
# lb_avt.place(x=20, y=120)


# Frame trang chính
def TK():
    framepage = Frame(padx=190, pady=0, width=811, height=561)
    framepage.place(x=229, y=0)

    table = DataTableFrame(framepage)
    table.place(width=300, height=565)


def MH():
    framepage = Frame(padx=190, pady=0, width=811, height=561)
    framepage.place(x=229, y=0)

    table = DataTableFrame_MonHoc(framepage)
    table.place(width=300, height=565)


def LH():
    framepage = Frame(padx=190, pady=0, width=811, height=561)
    framepage.place(x=229, y=0)

    table = DataTableFrame_LopHoc(framepage)
    table.place(width=300, height=565)


def HS():
    framepage = Frame(padx=190, pady=0, width=811, height=561)
    framepage.place(x=229, y=0)

    table = DataTableFrame_Hocsinh(framepage)
    table.place(width=811, height=565)


def Diem():
    framepage = Frame(padx=190, pady=0, width=811, height=561)
    framepage.place(x=229, y=0)

    table = FrameDiem(framepage)
    table.place(width=811, height=565)


def Logout():
    windown.destroy()
    os.system("python FormDangNhap.py")


# Button trong menu
btn_student = Button(framemenu, text=" Học Sinh", bg="lightblue", font=("Arial", 11, "")
                     , bd=0, image=icon_student, compound="left", command=HS)
btn_student.place(x=0, y=130, width=181, height=41)
btn_class = Button(framemenu, text=" Lớp Học", font=("Arial", 11, ""), image=icon_class, compound="left"
                   , bg="lightblue", bd=0, command=LH)
btn_class.place(x=0, y=170, width=181, height=41)
btn_subject = Button(framemenu, text=" Môn Học", font=("Arial", 11, ""), image=icon_subject, compound="left",
                     bg="lightblue", bd=0, command=MH)
btn_subject.place(x=0, y=210, width=181, height=41)
btn_score = Button(framemenu, text=" Điểm Số", bg="lightblue", font=("Arial", 11, ""), image=icon_score
                   , compound="left", bd=0, command=Diem)
btn_score.place(x=0, y=250, width=181, height=41)
btn_account = Button(framemenu, text=" Tài Khoản", bg="lightblue", image=icon_account
                     , font=("Arial", 11, ""), compound="left", bd=0, command=TK)
btn_account.place(x=0, y=290, width=181, height=41)
btn_logout = Button(framemenu, text=" Đăng xuất", bg="lightblue", bd=0,
                    font=("Arial", 11, ""), image=icon_logout, compound="left", command=Logout)
btn_logout.place(x=0, y=520, width=181, height=41)

windown.mainloop()
