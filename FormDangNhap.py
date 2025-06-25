import os
import sqlite3
import subprocess
import tkinter as tk

from PIL import Image
from tkinter import messagebox, ttk, Label, PhotoImage

from PIL.GifImagePlugin import GifImageFile

# Kết nối hoặc tạo CSDL
conn = sqlite3.connect("students.db")
cursor = conn.cursor()
cursor.execute('''
    CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY AUTOINCREMENT, 
        username TEXT UNIQUE,
        password TEXT
    )
''')
conn.commit()


def trangchu():
    login_window.destroy()
    os.system("python FormTrangChu.py")


def dangki():
    login_window.destroy()
    os.system("python FormDangKy.py")


# Hàm kiểm tra đăng nhập
def login():
    username = username_var.get()
    password = password_var.get()

    cursor.execute("SELECT * FROM users WHERE username = ? AND password = ?", (username, password))
    user = cursor.fetchone()

    if user:
        messagebox.showinfo("Thành công", "Đăng nhập thành công!")

        trangchu()

    else:
        messagebox.showerror("Lỗi", "Sai tên đăng nhập hoặc mật khẩu")


# Giao diện đăng nhập
login_window = tk.Tk()
login_window.title("Đăng Nhập")
login_window.geometry("988x473")
bg = PhotoImage(file="icon/img.png")
lb_bg = Label(login_window, image=bg)
lb_bg.place(width=988, height=473)

# icon
icon_register = PhotoImage(file="icon/register.png")
icon_log_in = PhotoImage(file="icon/log-in.png")

tk.Label(login_window, text="Tên đăng nhập:", background='white', font=('Arial', 11)).place(x=10, y=325)
username_var = tk.StringVar()
username_entry = tk.Entry(login_window, textvariable=username_var, font=('Arial', 11), background='lightblue',
                          borderwidth=3, relief='ridge')
username_entry.place(x=130, y=320, width=170, height=35)

tk.Label(login_window, text="Mật khẩu:", background='white', font=('Arial', 11)).place(x=10, y=365)
password_var = tk.StringVar()
password_entry = tk.Entry(login_window, textvariable=password_var, font=('Arial', 11), show="*", borderwidth=3,
                          relief='ridge', background='lightblue')
password_entry.place(x=130, y=360, width=170, height=35)

tk.Button(login_window, text="Đăng Nhập   ", font=("Arial", 11), image=icon_log_in, compound='right'
          , width=120, height=35, fg="#FFFFFF", background="#94AAD6", command=login).place(x=80, y=410)

tk.Button(login_window, text="Đăng Kí   ", font=("Arial", 11), image=icon_register, compound='right'
          , width=100, height=35, fg="#FFFFFF", background="#94AAD6", command=dangki).place(x=230, y=410)

login_window.mainloop()

conn.close()
