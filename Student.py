import sqlite3
import tkinter as tk
from tkinter import ttk, Entry, Label, StringVar, messagebox, Radiobutton


from PIL import ImageTk
from tkcalendar import DateEntry
from SearchHocSinh import DataTableFrame_Timkiem


class DataTableFrame_Hocsinh(ttk.Frame):
    def __init__(self, parent):
        super().__init__(parent)

        conn = sqlite3.connect("students.db")
        cur = conn.cursor()
        framebtn = tk.Frame(padx=20, pady=250, width=781, height=261)
        framebtn.place(x=229, y=20)
        frametable = tk.Frame(padx=20, pady=10, width=781, height=261)
        frametable.place(x=229, y=0)
        # Tạo bảng dữ liệu
        columns = (
        "Mã học sinh", "Tên học sinh", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Mã lớp", "Ghi chú")
        self.tree = ttk.Treeview(frametable, columns=columns, show="headings")

        # Đặt tên cột
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=95, anchor="center")

        def timkiem():
            framepage = tk.Frame(padx=190, pady=0, width=811, height=561)
            framepage.place(x=229, y=0)

            table = DataTableFrame_Timkiem(framepage)
            table.place(width=300, height=565)

        def showdata():
            data = cur.execute("Select *  from HocSinh")
            for item in data:
                self.tree.insert("", "end", values=item)

            self.tree.pack(pady=10)

        showdata()

        # 1️⃣ Họ tên học sinh
        lb_hoten = tk.Label(framebtn, text="Họ tên học sinh:")
        lb_hoten.grid(row=0, column=0, sticky="w", padx=5, pady=2)
        tenhs = tk.StringVar()
        entry_hoten = ttk.Entry(framebtn, textvariable=tenhs, width=30)
        entry_hoten.grid(row=0, column=1, sticky="w", padx=5, pady=10)

        # 2️⃣ Giới tính
        lb_gioitinh = tk.Label(framebtn, text="Giới tính:")
        lb_gioitinh.grid(row=1, column=0, sticky="w", padx=5, pady=10)
        var_gioitinh = tk.StringVar(value="Nam")
        frame_gioitinh = tk.Frame(framebtn)
        frame_gioitinh.grid(row=1, column=1, sticky="w", padx=5, pady=10)
        rb_nam = ttk.Radiobutton(frame_gioitinh, text="Nam", variable=var_gioitinh, value="Nam")
        rb_nu = ttk.Radiobutton(frame_gioitinh, text="Nữ", variable=var_gioitinh, value="Nữ")
        rb_nam.pack(side="left", padx=5)
        rb_nu.pack(side="left")

        # 3️⃣ Ngày sinh
        lb_ngaysinh = tk.Label(framebtn, text="Ngày sinh:")
        lb_ngaysinh.grid(row=2, column=0, sticky="w", padx=5, pady=10)
        entry_ngaysinh = DateEntry(framebtn, date_pattern="dd-mm-yyyy", width=28)
        entry_ngaysinh.grid(row=2, column=1, sticky="w", padx=5, pady=10)

        # 4️⃣ Số điện thoại
        lb_sdt = tk.Label(framebtn, text="Số điện thoại:")
        lb_sdt.grid(row=0, column=3, sticky="w", padx=5, pady=10)
        sdt = tk.StringVar()
        entry_sdt = ttk.Entry(framebtn, textvariable=sdt, width=30)
        entry_sdt.grid(row=0, column=4, sticky="w", padx=5, pady=10)

        # 5️⃣ Địa chỉ
        lb_diachi = tk.Label(framebtn, text="Địa chỉ:")
        lb_diachi.grid(row=1, column=3, sticky="w", padx=5, pady=10)
        diachi = tk.StringVar()
        entry_diachi = ttk.Entry(framebtn, textvariable=diachi, width=30)
        entry_diachi.grid(row=1, column=4, sticky="w", padx=5, pady=10)

        # 6️⃣ Mã lớp
        lb_malop = tk.Label(framebtn, text="Mã lớp:")
        lb_malop.grid(row=2, column=3, sticky="w", padx=5, pady=10)
        combo_malop = ttk.Combobox(framebtn, state="readonly", width=28)
        combo_malop.grid(row=2, column=4, sticky="w", padx=5, pady=10)

        # 7️⃣ Ghi chú
        lb_ghichu = tk.Label(framebtn, text="Ghi chú:")
        lb_ghichu.grid(row=6, column=0, sticky="w", padx=5, pady=10)
        ghichu = tk.StringVar()
        entry_ghichu = ttk.Entry(framebtn, textvariable=ghichu, width=30)
        entry_ghichu.grid(row=6, column=1, sticky="w", padx=5, pady=10)

        def load_danh_sach_lop():
            with sqlite3.connect("students.db") as db:
                cur.execute("SELECT MaLop, TenLop FROM LopHoc")
                rows = cur.fetchall()
                combo_malop["values"] = [f"{r[0]} - {r[1]}" for r in rows]

        def them_hoc_sinh():
            ho_ten = entry_hoten.get()
            gioi_tinh = var_gioitinh.get()
            ngay_sinh = entry_ngaysinh.get()
            sdt = entry_sdt.get()
            dia_chi = entry_diachi.get()
            malop_text = combo_malop.get()
            ghi_chu = entry_ghichu.get()

            if not all([ho_ten, ngay_sinh, sdt, dia_chi, malop_text]):
                messagebox.showerror("Lỗi", "Vui lòng điền đầy đủ thông tin!")
                return
            if len(sdt) != 10 or not sdt.isdigit():
                messagebox.showerror("Lỗi", "Số điện thoại phải có 10 chữ số!")
                return

            ma_lop = malop_text.split(" - ")[0]

            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute(
                    "SELECT MIN(t1.MaHocSinh + 1) FROM HocSinh t1 LEFT JOIN HocSinh t2 ON t1.MaHocSinh + 1 = t2.MaHocSinh WHERE t2.MaHocSinh IS NULL")
                new_id = cur.fetchone()[0]

                if new_id is None:  # Nếu không có ID bị thiếu, lấy ID tiếp theo
                    cur.execute("SELECT COALESCE(MAX(MaHocSinh), 0) + 1 FROM HocSinh")
                    new_id = cur.fetchone()[0]

                cur.execute("""
                        INSERT INTO HocSinh (MaHocSinh, HoTen, GioiTinh, NgaySinh, SDT, DiaChi, MaLop, GhiChu)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?)""",
                            (new_id, ho_ten, gioi_tinh, ngay_sinh, sdt, dia_chi, ma_lop, ghi_chu))

                db.commit()
            messagebox.showinfo("Thành công", "Đã thêm học sinh thành công!")
            clear_entries()
            hien_thi_ds()

        def sua_hoc_sinh():
            selected = self.tree.focus()
            if not selected:
                messagebox.showerror("Lỗi", "Vui lòng chọn học sinh cần sửa!")
                return
            values = self.tree.item(selected, "values")
            ma_hs = values[0]

            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("""
                UPDATE HocSinh SET HoTen=?, GioiTinh=?, NgaySinh=?, SDT=?, DiaChi=?, Malop=?, GhiChu=? WHERE MaHocSinh=?
                """, (entry_hoten.get(), var_gioitinh.get(), entry_ngaysinh.get(), entry_sdt.get(), entry_diachi.get(),
                      combo_malop.get(), entry_ghichu.get(), ma_hs))
                db.commit()
                messagebox.showinfo("Thành công", "Đã cập nhật thông tin học sinh!")
                clear_entries()
                hien_thi_ds()

        def xoa_hoc_sinh():
            selected = self.tree.focus()
            if not selected:
                messagebox.showerror("Lỗi", "Vui lòng chọn học sinh cần xóa!")
                return
            values = self.tree.item(selected, "values")
            ma_hs = values[0]

            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("DELETE FROM HocSinh WHERE MaHocSinh=?", (ma_hs,))
                db.commit()
                messagebox.showinfo("Thành công", "Đã xóa học sinh!")
                clear_entries()
                hien_thi_ds()

        def hien_thi_ds():
            for row in self.tree.get_children():
                self.tree.delete(row)
            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("SELECT * FROM HocSinh")
                for row in cur.fetchall():
                    self.tree.insert("", tk.END, values=row)

        def chon_hs(_):
            selected = self.tree.focus()
            if not selected:
                return
            values = self.tree.item(selected, "values")
            entry_hoten.delete(0, tk.END)
            entry_hoten.insert(0, values[1])
            var_gioitinh.set(values[2])
            entry_ngaysinh.set_date(values[3])
            entry_sdt.delete(0, tk.END)
            entry_sdt.insert(0, values[4])
            entry_diachi.delete(0, tk.END)
            entry_diachi.insert(0, values[5])
            combo_malop.set(values[6])
            entry_ghichu.delete(0, tk.END)
            entry_ghichu.insert(0, values[7])

        def clear_entries():
            entry_hoten.delete(0, tk.END)
            var_gioitinh.set("Nam")
            entry_ngaysinh.set_date("01-01-2000")
            entry_sdt.delete(0, tk.END)
            entry_diachi.delete(0, tk.END)
            combo_malop.set("")
            entry_ghichu.delete(0, tk.END)

        load_danh_sach_lop()
        hien_thi_ds()
        self.tree.bind("<ButtonRelease-1>", chon_hs)
        # iconn
        icon_update = ImageTk.PhotoImage(file="icon/update.png")
        icon_add = ImageTk.PhotoImage(file="icon/add.png")
        icon_delete = ImageTk.PhotoImage(file="icon/delete .png")
        icon_search = ImageTk.PhotoImage(file="icon/search.png")

        btn_addHS = tk.Button(framebtn, text="Thêm    ", font=("Arial", 11), image=icon_add, compound='right',
                              command=them_hoc_sinh, width=100, height=25, background='#65B446', fg='#FFFFFF')
        btn_addHS.image = icon_add

        btn_updateHS = tk.Button(framebtn, text="Sửa  ", font=("Arial", 11), image=icon_update, compound='right'
                                 , width=100, height=25, background="#F1AF00", fg="#FFFFFF", command=sua_hoc_sinh)
        btn_updateHS.image = icon_update
        btn_deleteHS = tk.Button(framebtn, text="Xóa  ", font=("Arial", 11), image=icon_delete, compound='right'
                                 , width=100, height=25, fg="#FFFFFF", background="#C82E31", command=xoa_hoc_sinh)
        btn_deleteHS.image = icon_delete
        btn_search = tk.Button(framebtn, text="Tìm Kiếm  ", font=("Arial", 11), image=icon_search, compound='right'
                               , width=100, height=25, fg="#FFFFFF", background="#00676B", command=timkiem)
        btn_search.image = icon_search

        btn_addHS.grid(row=7, column=0, padx=0, pady=10)
        btn_updateHS.grid(row=7, column=1, padx=0, pady=10)
        btn_deleteHS.grid(row=7, column=2, padx=0, pady=10)
        btn_search.grid(row=7, column=4, padx=0, pady=10)
