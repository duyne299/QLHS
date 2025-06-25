import sqlite3
import tkinter as tk
from tkinter import ttk, messagebox, Entry
from tracemalloc import Frame

from PIL import ImageTk


class DataTableFrame_Timkiem(ttk.Frame):

    def __init__(self, parent):
        super().__init__(parent)

        # Kết nối CSDL và lưu vào self để dùng sau
        conn = sqlite3.connect("students.db")
        cur = conn.cursor()


        # Tạo frame
        framebtn = tk.Frame(padx=20, pady=250, width=781, height=261)
        framebtn.place(x=229, y=20)
        frametable = tk.Frame(padx=15, pady=10, width=781, height=261)
        frametable.place(x=229, y=0)

        # Tạo bảng dữ liệu
        columns = (
        "Mã học sinh", "Tên học sinh", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Mã lớp", "Ghi chú")
        self.tree = ttk.Treeview(frametable, columns=columns, show="headings")

        # Đặt tên cột
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=85,anchor="center")

        self.tree.pack(pady=10)

        #Giao diện btn
        # Mã học sinh
        tk.Label(framebtn, text="Tìm kiếm theo Mã học sinh").grid(row=0, column=0, padx=10, pady=10, sticky="w")
        cmb_hs = ttk.Entry(framebtn)
        cmb_hs.grid(row=0, column=1, padx=10, pady=15)

        # Mã môn học
        tk.Label(framebtn, text="Tìm kiếm theo Mã lớp").grid(row=0, column=3, padx=10, pady=15, sticky="w")
        cmb_ml = ttk.Combobox(framebtn)
        cmb_ml.grid(row=0, column=4, padx=10, pady=15)




        def lay_ds_hoc_sinh():
            cur.execute("SELECT MaHocSinh, HoTen FROM HocSinh")
            return cur.fetchall()

        def lay_ds_lop_hoc():
            cur.execute("SELECT MaLop, TenLop FROM LopHoc")
            return cur.fetchall()


        def cap_nhat_combobox():
            """Cập nhật dữ liệu cho Combobox"""

            ds_ml = [f"{ml[0]} - {ml[1]}" for ml in lay_ds_lop_hoc()]


            cmb_ml["values"] = ds_ml

        def hien_thi_diem():
            """Hiển thị điểm trong bảng"""
            for row in self.tree.get_children():
                self.tree.delete(row)

            cur.execute("""
               SELECT HocSinh.MaHocSinh, HocSinh.HoTen, HocSinh.GioiTinh, HocSinh.NgaySinh, 
                          HocSinh.SDT, HocSinh.DiaChi, HocSinh.MaLop, HocSinh.GhiChu
                   FROM HocSinh
                   
                   """)

            for row in cur.fetchall():
                self.tree.insert("", "end", values=row)
                self.tree.pack(pady=10)

        def search_students():
            mahs = cmb_hs.get().strip()
            malop = cmb_ml.get().strip()

            if not mahs and not malop:
                messagebox.showwarning("Cảnh báo", "Vui lòng nhập mã học sinh hoặc mã lớp!")
                return


            if mahs:
                cur.execute("SELECT * FROM HocSinh WHERE MaHocSinh = ?", (mahs,))
            else:
                cur.execute("SELECT * FROM HocSinh WHERE MaLop = ?", (malop,))

            rows = cur.fetchall()


            # Xóa dữ liệu cũ trong Treeview
            for row in self.tree.get_children():
                self.tree.delete(row)

            if not rows:
                messagebox.showinfo("Thông báo", "Không tìm thấy thông tin học sinh!")
                return

            # Hiển thị kết quả tìm kiếm
            for row in rows:
                self.tree.insert("", "end", values=row)

            # Chỉ xóa nội dung trong Entry
            cmb_hs.delete(0, tk.END)
            cmb_ml.delete(0, tk.END)

        # iconn

        icon_export = ImageTk.PhotoImage(file="icon/export.png")
        icon_search = ImageTk.PhotoImage(file="icon/search.png")
        # Nút thao tác
        btn_search = tk.Button(framebtn, text="Tìm Kiếm    ", font=("Arial", 11), image=icon_search, compound='right',
                              width=110, height=25, background='#00676B', fg='#FFFFFF',command=search_students)
        btn_search.image = icon_search

        btn_search.grid(row=5, column=0, padx=0, pady=10)



        # Cập nhật dữ liệu
        cap_nhat_combobox()
        hien_thi_diem()
