import os
import sqlite3
import tkinter as tk
from tkinter import messagebox, ttk, Label, mainloop, PhotoImage, filedialog
from PIL import Image, ImageTk

# Kết nối hoặc tạo CSDL
conn = sqlite3.connect("students.db")
cursor = conn.cursor()


def dangnhap():
    register_window.destroy()

    os.system("python FormDangNhap.py")


def submit_registration():
    new_username = new_username_var.get()
    new_password = new_password_var.get()
    acp_password = acp_password_var.get()
    if new_username and new_password and new_password == acp_password:
        try:
            cursor.execute("INSERT INTO users (username, password) VALUES (?, ?)", (new_username, new_password))
            conn.commit()
            messagebox.showinfo("Thành công", "Đăng ký thành công!")
            dangnhap()


        except sqlite3.IntegrityError:
            messagebox.showerror("Lỗi", "Tên đăng nhập đã tồn tại!")
    elif new_password != acp_password:
        messagebox.showwarning("Lỗi", "Mật khẩu không khớp .Vui lòng nhập lại mật khẩu.")
    else:
        messagebox.showwarning("Lỗi", "Vui lòng nhập đầy đủ thông tin ")


register_window = tk.Tk()
register_window.title("Đăng Ký")
register_window.geometry("581x472")
# # Label hiển thị ảnh đại diện
# lbl_avatar = Label(register_window, text="", width=100, height=100)
# lbl_avatar.place(x=400,y=0)
#
# # Nút chọn ảnh đại diện
# btn_upload = tk.Button(register_window, text="Chọn ảnh đại diện", command=upload_image)
# btn_upload.place(x=400,y=150)
show_password_var = tk.BooleanVar()
# Tên đăng nhập
Label(register_window, text="Tên đăng nhập:").grid(row=1, column=0, padx=10, pady=5, sticky="w")
new_username_var = tk.StringVar()
new_username_entry = tk.Entry(register_window, textvariable=new_username_var)
new_username_entry.grid(row=1, column=1, padx=10, pady=5)

# Mật khẩu
Label(register_window, text="Mật khẩu:").grid(row=2, column=0, padx=10, pady=5, sticky="w")
new_password_var = tk.StringVar()
new_password_entry = tk.Entry(register_window, textvariable=new_password_var, show="*")
new_password_entry.grid(row=2, column=1, padx=10, pady=5)

# Xác nhận mật khẩu
Label(register_window, text="Xác nhận mật khẩu:").grid(row=3, column=0, padx=10, pady=5, sticky="w")
acp_password_var = tk.StringVar()
acp_password_entry = tk.Entry(register_window, textvariable=acp_password_var, show="*")
acp_password_entry.grid(row=3, column=1, padx=10, pady=5)


def show_password():
    if show_password_var.get():
        new_password_entry.config(show='')
        acp_password_entry.config(show='')
    else:
        new_password_entry.config(show="*")
        acp_password_entry.config(show="*")


# Checkbox để hiển thị mật khẩu
show_password_cb = tk.Checkbutton(register_window, text="Hiển thị mật khẩu",
                                  variable=show_password_var,
                                  command=show_password)

btn_dk = tk.Button(register_window, text="Đăng ký", command=submit_registration)
btn_dk.grid(row=4, column=1, pady=20)
bg = PhotoImage(file="icon/class.png")
register_window.mainloop()
