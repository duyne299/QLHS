import os
import sqlite3
from tkinter import *
import tkinter as tk
from tkinter import ttk

import os

import icon
from tkinter import ttk, Entry, Label, StringVar, messagebox, END
from PIL import Image, ImageTk


class DataTableFrame(ttk.Frame):
    def __init__(self, parent):
        super().__init__(parent)

        conn = sqlite3.connect("students.db")
        cur = conn.cursor()
        framebtn = Frame(padx=20, pady=250, width=781, height=261)
        framebtn.place(x=229, y=20)
        frametable = Frame(padx=20, pady=10, width=781, height=261)
        frametable.place(x=229, y=0)

        # Tạo bảng dữ liệu
        columns = ("Tài khoản", "Mật khẩu")
        self.tree = ttk.Treeview(frametable, columns=columns, show="headings")
        self.tree.place(x=110, y=10, width=651, height=271)

        # Đặt tên cột
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col,width=300,  anchor="center")

        def showdata():

            data = cur.execute("Select username,password  from users")
            for item in data:
                self.tree.insert("", "end", values=item)

            self.tree.pack(pady=10)

        showdata()

        # Nhập tên tài khoản
        lb_us = Label(framebtn, text="Nhập tên tài khoản")
        lb_us.grid(row=0, column=0, padx=10, pady=15, sticky="w")

        uservar = StringVar()
        entry_user = Entry(framebtn, textvariable=uservar)
        entry_user.grid(row=0, column=1, padx=10, pady=15)

        # Nhập mật khẩu
        lb_pass = Label(framebtn, text="Nhập mật khẩu: ")
        lb_pass.grid(row=1, column=0, padx=10, pady=15, sticky="w")

        passvar = StringVar()
        entry_pass = Entry(framebtn, textvariable=passvar, show="*")  # Dùng show="*" để ẩn mật khẩu
        entry_pass.grid(row=1, column=1, padx=10, pady=15)

        def Add_user():
            user = uservar.get()
            passw = passvar.get()
            if user and passw:
                try:
                    cur.execute("INSERT INTO users (username, password) VALUES (?, ?)", (user, passw))
                    conn.commit()
                    messagebox.showinfo("Thành công", "Thêm tài khoản thành công!")
                    entry_pass.delete(0, END)
                    entry_user.delete(0, END)
                    self.clear_table()
                    showdata()
                except sqlite3.IntegrityError:
                    messagebox.showerror("Lỗi", "Tên đăng nhập đã tồn tại!")

            else:
                messagebox.showwarning("Lỗi", "Vui lòng nhập đầy đủ thông tin ")

        def delete():

            selected = self.tree.focus()
            if not selected:
                messagebox.showerror("Lỗi", "Vui lòng chọn tài khoản cần xóa!")
                return
            values = self.tree.item(selected, "values")
            ma = values[0]
            cur.execute("DELETE FROM users WHERE username = ? ", (ma,))
            conn.commit()
            self.clear_table()
            showdata()

        def Update():
            selected = self.tree.focus()
            if not selected:
                messagebox.showerror("Lỗi", "Vui lòng chọn tài khoản cần sửa!")
                return
            values = self.tree.item(selected, "values")
            ma = values[0]
            cur.execute("UPDATE users SET username = ?,password=? WHERE username=? ",
                        (entry_user.get(), entry_pass.get(), ma))
            conn.commit()
            messagebox.showinfo("Thành công", "Đã cập nhật thông tin tài khoản !")
            entry_pass.delete(0, END)
            entry_user.delete(0, END)
            self.clear_table()
            showdata()

        #iconn
        icon_update=ImageTk.PhotoImage(file="icon/update.png")
        icon_add = ImageTk.PhotoImage(file="icon/add.png")
        icon_delete = ImageTk.PhotoImage(file="icon/delete .png")

        # Tạo button với ảnh
        btn_add = tk.Button(framebtn, text="Thêm    ", font=("Arial", 11), image=icon_add, compound='right',
                            command=Add_user, width=100, height=25, background='#65B446', fg='#FFFFFF')

        btn_add.image = icon_add

        btn_delete = tk.Button(framebtn, text="Xóa  ", font=("Arial", 11), image=icon_delete, compound='right'
                               , width=100, height=25,fg="#FFFFFF",background="#C82E31" ,command=delete)
        btn_delete.image = icon_delete

        btn_update = tk.Button(framebtn, text="Sửa  ", font=("Arial", 11), image=icon_update, compound='right'
                               , width=100, height=25,background="#F1AF00",fg="#FFFFFF", command=Update)
        btn_update.image = icon_update
        btn_add.grid(row=2,column=0 ,padx=15, pady=20)
        btn_update.grid(row=2,column=1, padx=15, pady=20)
        btn_delete.grid(row=2,column=2, padx=15, pady=20)


    def clear_table(self):
        """Xóa tất cả dữ liệu trong bảng."""
        for item in self.tree.get_children():
            self.tree.delete(item)
