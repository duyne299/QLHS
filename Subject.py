import sqlite3
import tkinter as tk
from tkinter import ttk, Entry, Label, StringVar, messagebox, END
from PIL import Image, ImageTk

class DataTableFrame_MonHoc(ttk.Frame):
    def __init__(self, parent):
        super().__init__(parent)

        conn = sqlite3.connect("students.db")
        cur = conn.cursor()
        framebtn = tk.Frame(padx=20, pady=280, width=781, height=261)
        framebtn.place(x=229, y=20)
        frametable = tk.Frame(padx=20, pady=10, width=781, height=261)
        frametable.place(x=229, y=0)
        # Tạo bảng dữ liệu
        columns = ("Mã môn học","Tên môn học","Số tiết")
        self.tree = ttk.Treeview(frametable, columns=columns, show="headings")


        # Đặt tên cột
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=200,anchor="center")

        def showdata():
            for row in self.tree.get_children():
                self.tree.delete(row)
            data = cur.execute("Select *  from Monhoc")
            for item in data:
                self.tree.insert("", "end", values=item)

            self.tree.pack(pady=10)
        showdata()

        #iconbutton

        icon_update = ImageTk.PhotoImage(file="icon/update.png")
        icon_add = ImageTk.PhotoImage(file="icon/add.png")
        icon_delete = ImageTk.PhotoImage(file="icon/delete .png")

        # Mã môn học
        lb_mamh = tk.Label(framebtn, text="Mã môn học: ")
        lb_mamh.grid(row=0, column=0, padx=10, pady=5, sticky="w")

        mamh = StringVar()
        entry_mamh = Entry(framebtn, textvariable=mamh)
        entry_mamh.grid(row=0, column=1, padx=10, pady=15)

        # Tên môn học
        lb_tenmh = tk.Label(framebtn, text="Tên môn học: ")
        lb_tenmh.grid(row=1, column=0, padx=10, pady=15, sticky="w")

        tenmh = StringVar()
        entry_tenmh = Entry(framebtn, textvariable=tenmh)
        entry_tenmh.grid(row=1, column=1, padx=10, pady=15)

        # Số tiết
        lb_sotiet = tk.Label(framebtn, text="Số tiết: ")
        lb_sotiet.grid(row=0, column=3, padx=10, pady=15, sticky="w")

        sotiet = StringVar()
        entry_sotiet = Entry(framebtn, textvariable=sotiet)
        entry_sotiet.grid(row=0, column=4, padx=10, pady=15)





        def AddMH():
            mamonhoc = entry_mamh.get()
            tenmonhoc = entry_tenmh.get()
            sotiet = entry_sotiet.get()
            if mamonhoc and tenmonhoc and sotiet:
                try:
                    cur.execute(" INSERT INTO Monhoc VALUES (?,?,?)", (mamonhoc, tenmonhoc, sotiet))
                    conn.commit()
                    messagebox.showinfo("Sucessful", "Thêm môn học thành công. ")
                    entry_mamh.delete(0,END)
                    entry_tenmh.delete(0,END)
                    entry_sotiet.delete(0,END)
                    showdata()
                    for i in cur.fetchall():
                        print(i)
                except sqlite3.Error as e:
                    messagebox.showerror(f"Lỗi : {e}.")
            else:
                messagebox.showwarning("Warning", "Vui lòng nhập đầy đủ thông tin.")


        def UpdateMH():
            mamonhoc = entry_mamh.get()
            tenmonhoc = entry_tenmh.get()
            sotiet = entry_sotiet.get()
            if mamonhoc and tenmonhoc and sotiet:
                try:
                    cur.execute("UPDATE Monhoc SET TenMH = ?, SoTiet = ? WHERE MaMH = ?",
                                (tenmonhoc, sotiet, mamonhoc))
                    conn.commit()
                    messagebox.showinfo("Sucessful", "Sửa môn học thành công.")
                    entry_mamh.delete(0, END)
                    entry_tenmh.delete(0, END)
                    entry_sotiet.delete(0, END)
                    showdata()
                except sqlite3.Error as e:
                    messagebox.showerror(f"Lỗi: {e}.")
            else:
                messagebox.showwarning("Warning", "Vui lòng nhập mã môn học để sửa.")

        def DeleteMH():
            mamonhoc = entry_mamh.get()
            if mamonhoc:
                try:
                    cur.execute("DELETE FROM Monhoc WHERE MaMH = ?", (mamonhoc,))
                    conn.commit()
                    messagebox.showinfo("Sucessful", "Xóa môn học thành công.")
                    entry_mamh.delete(0, END)
                    showdata()
                except sqlite3.Error as e:
                    messagebox.showerror(f"Lỗi: {e}.")
            else:
                messagebox.showwarning("Warning", "Vui lòng nhập mã môn học để xóa.")


        btn_addMH = tk.Button(framebtn, text="Thêm    ", font=("Arial", 11), image=icon_add, compound='right',
                            command=AddMH, width=100, height=25, background='#65B446', fg='#FFFFFF')
        btn_addMH.image=icon_add
        btn_updateMH = tk.Button(framebtn, text="Sửa  ", font=("Arial", 11), image=icon_update, compound='right'
                               , width=100, height=25,background="#F1AF00",fg="#FFFFFF", command=UpdateMH)
        btn_updateMH.image=icon_update
        btn_deleteMH = tk.Button(framebtn, text="Xóa  ", font=("Arial", 11), image=icon_delete, compound='right'
                               , width=100, height=25,fg="#FFFFFF",background="#C82E31" ,command=DeleteMH)
        btn_deleteMH.image=icon_delete

        btn_addMH.grid(row=3, column=0, padx=10, pady=10)
        btn_updateMH.grid(row=3, column=1, padx=10, pady=10)
        btn_deleteMH.grid(row=3, column=2, padx=10, pady=10)





