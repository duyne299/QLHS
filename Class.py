import sqlite3
import tkinter as tk
from tkinter import ttk, Entry, Label, StringVar, messagebox

from PIL import ImageTk


class DataTableFrame_LopHoc(ttk.Frame):
    def __init__(self, parent):
        super().__init__(parent)

        conn = sqlite3.connect("students.db")
        cur = conn.cursor()

        framebtn = tk.Frame(padx=20, pady=280, width=781, height=261)
        framebtn.place(x=229, y=20)
        frametable = tk.Frame(padx=20, pady=10, width=781, height=261)
        frametable.place(x=229, y=0)
        # Tạo bảng dữ liệu
        columns = ("Mã lớp học", "Tên lớp học", "Khối học")
        self.tree = ttk.Treeview(frametable, columns=columns, show="headings")

        # Đặt tên cột
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=220,anchor="center")

        def showdata():
            data = cur.execute("Select *  from LopHoc")
            for item in data:
                self.tree.insert("", "end", values=item)

            self.tree.pack(pady=10)

        showdata()
        # Mã lớp học
        lb_malop = tk.Label(framebtn, text="Mã lớp học: ")
        lb_malop.grid(row=0, column=0, padx=10, pady=15, sticky="w")

        malop = StringVar()
        entry_malop = Entry(framebtn, textvariable=malop)
        entry_malop.grid(row=0, column=1, padx=10, pady=15)

        # Tên lớp học
        lb_tenlop = tk.Label(framebtn, text="Tên lớp học: ")
        lb_tenlop.grid(row=1, column=0, padx=10, pady=15, sticky="w")

        tenlop = StringVar()
        entry_tenlop = Entry(framebtn, textvariable=tenlop)
        entry_tenlop.grid(row=1, column=1, padx=10, pady=15)

        # Khối học
        lb_khoihoc = tk.Label(framebtn, text="Khối học: ")
        lb_khoihoc.grid(row=0, column=3, padx=10, pady=15, sticky="w")

        khoi_options = [
            "A (Toán - Lý - Hóa)",
            "A1 (Toán - Lý - Anh)",
            "B (Toán - Hóa - Sinh)",
            "C (Văn - Sử - Địa)",
            "D (Toán - Văn - Anh)"
        ]

        comb_khoihoc = ttk.Combobox(framebtn, values=khoi_options, state="readonly")
        comb_khoihoc.grid(row=0, column=4, padx=10, pady=15)

        def get_next_ma_lop():
            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("SELECT MaLop FROM LopHoc ORDER BY MaLop")
                used_ids = [row[0] for row in cur.fetchall()]

                if not used_ids:
                    return 1
                for i in range(1, max(used_ids) + 2):
                    if i not in used_ids:
                        return i

        def hien_thi_ds_lop():
            for row in self.tree.get_children():
                self.tree.delete(row)
            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("SELECT * FROM LopHoc")
                rows = cur.fetchall()
                for row in rows:
                    self.tree.insert("", tk.END, values=row)

        def them_lop():
            ten_lop = entry_tenlop.get()
            khoi_hoc = comb_khoihoc.get()
            if not ten_lop or not khoi_hoc:
                messagebox.showerror("Lỗi", "Vui lòng nhập đầy đủ thông tin lớp học!")
                return

            with sqlite3.connect("students.db") as db:
                cur = db.cursor()

                # Kiểm tra xem tên lớp đã tồn tại chưa
                cur.execute("SELECT COUNT(*) FROM LopHoc WHERE TenLop = ?", (ten_lop,))
                if cur.fetchone()[0] > 0:
                    messagebox.showerror("Lỗi", f"Lớp '{ten_lop}' đã tồn tại! Vui lòng nhập tên lớp khác.")
                    return

                # Lấy mã lớp hợp lệ
                ma_lop = get_next_ma_lop()
                cur.execute("INSERT INTO LopHoc (MaLop, TenLop, KhoiHoc) VALUES (?, ?, ?)", (ma_lop, ten_lop, khoi_hoc))
                db.commit()

                messagebox.showinfo("Thành công", f"Đã thêm lớp học có mã {ma_lop}!")
                hien_thi_ds_lop()
                clear_entries()

        def sua_lop():
            selected = self.tree.focus()
            if not selected:
                messagebox.showerror("Lỗi", "Vui lòng chọn lớp học cần sửa!")
                return
            values = self.tree.item(selected, "values")
            ma_lop = values[0]
            ten_lop = entry_tenlop.get()
            khoi_hoc = comb_khoihoc.get()
            if not ten_lop or not khoi_hoc:
                messagebox.showerror("Lỗi", "Vui lòng nhập đầy đủ thông tin lớp học!")
                return
            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("UPDATE LopHoc SET TenLop=?, KhoiHoc=? WHERE MaLop=?", (ten_lop, khoi_hoc, ma_lop))
                db.commit()
                messagebox.showinfo("Thành công", "Đã cập nhật lớp học!")
                hien_thi_ds_lop()
                clear_entries()

        def xoa_lop():
            selected = self.tree.focus()
            if not selected:
                messagebox.showerror("Lỗi", "Vui lòng chọn lớp học cần xóa!")
                return
            values = self.tree.item(selected, "values")
            ma_lop = values[0]
            with sqlite3.connect("students.db") as db:
                cur = db.cursor()
                cur.execute("DELETE FROM LopHoc WHERE MaLop=?", (ma_lop,))
                db.commit()
                messagebox.showinfo("Thành công", f"Đã xóa lớp học có mã {ma_lop}!")
                hien_thi_ds_lop()
                clear_entries()

        def chon_lop(event):
            selected = self.tree.focus()
            if not selected:
                return
            values = self.tree.item(selected, "values")
            entry_tenlop.delete(0, tk.END)
            entry_tenlop.insert(0, values[1])
            comb_khoihoc.set(values[2])

        def clear_entries():
            entry_malop.delete(0,tk.END)
            entry_tenlop.delete(0, tk.END)
            comb_khoihoc.set("")

        # iconn
        icon_update = ImageTk.PhotoImage(file="icon/update.png")
        icon_add = ImageTk.PhotoImage(file="icon/add.png")
        icon_delete = ImageTk.PhotoImage(file="icon/delete .png")
        btn_addLH = tk.Button(framebtn, text="Thêm    ", font=("Arial", 11), image=icon_add, compound='right',
                            command=them_lop, width=100, height=25, background='#65B446', fg='#FFFFFF')
        btn_addLH.image=icon_add
        btn_updateLH = tk.Button(framebtn, text="Sửa  ", font=("Arial", 11), image=icon_update, compound='right'
                               , width=100, height=25,background="#F1AF00",fg="#FFFFFF", command=sua_lop)
        btn_updateLH.image=icon_update
        btn_deleteLH = tk.Button(framebtn, text="Xóa  ", font=("Arial", 11), image=icon_delete, compound='right'
                               , width=100, height=25,fg="#FFFFFF",background="#C82E31" ,command=xoa_lop)
        btn_deleteLH.image=icon_delete

        btn_addLH.grid(row=3, column=0, padx=5,pady=10)
        btn_updateLH.grid(row=3, column=1, padx=5,pady=10)
        btn_deleteLH.grid(row=3, column=2, padx=5,pady=10)
