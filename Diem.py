import sqlite3
import tkinter as tk
from tkinter import ttk, messagebox, Entry

from PIL import ImageTk
from SearchDiem import FrameSearchDiem


class FrameDiem(ttk.Frame):

    def __init__(self, parent):
        super().__init__(parent)

        # Kết nối CSDL và lưu vào self để dùng sau
        conn = sqlite3.connect("students.db")
        cur = conn.cursor()

        cur.execute('''
        CREATE TABLE IF NOT EXISTS DiemSo (
            MaHocSinh INTEGER,
            MaMonHoc INTEGER,
             MaLop INTEGER,
            HocKy TEXT NOT NULL,
            DiemMieng REAL NOT NULL,
            Diem15Phut REAL NOT NULL,
            Diem1Tiet REAL NOT NULL,
            DiemHocKy REAL NOT NULL,
            DiemTrungBinh REAL NOT NULL,
            PRIMARY KEY (MaHocSinh, MaMonHoc, HocKy),
            FOREIGN KEY (MaHocSinh) REFERENCES HocSinh(MaHocSinh),
            FOREIGN KEY (MaMonHoc) REFERENCES MonHoc(MaMH)
        )
        ''')
        conn.commit()
        # Tạo frame
        framebtn = tk.Frame(padx=20, pady=250, width=781, height=261)
        framebtn.place(x=229, y=20)
        frametable = tk.Frame(padx=15, pady=10, width=781, height=261)
        frametable.place(x=229, y=0)

        # Tạo bảng dữ liệu
        columns = (
            "Mã HS", "Họ tên", "Tên MH", "Học kỳ", "Điểm miệng", "Điểm 15 phút", "Điểm 1 tiết", "Điểm HK", "Điểm TB")
        self.tree = ttk.Treeview(frametable, columns=columns, show="headings")

        # Đặt tên cột
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=85,anchor="center")

        self.tree.pack(pady=10)

        #Giao diện btn
        # Mã học sinh
        tk.Label(framebtn, text="Mã học sinh").grid(row=0, column=0, padx=10, pady=10, sticky="w")
        cmb_hs = ttk.Combobox(framebtn)
        cmb_hs.grid(row=0, column=1, padx=10, pady=15)

        # Mã môn học
        tk.Label(framebtn, text="Mã môn học").grid(row=1, column=0, padx=10, pady=15, sticky="w")
        cmb_mh = ttk.Combobox(framebtn)
        cmb_mh.grid(row=1, column=1, padx=10, pady=15)

        # Học kỳ
        tk.Label(framebtn, text="Học kỳ").grid(row=2, column=0, padx=10, pady=15, sticky="w")
        cmb_hocky = ttk.Combobox(framebtn, values=["HK1", "HK2"])
        cmb_hocky.grid(row=2, column=1, padx=10, pady=15)

        # Điểm miệng
        tk.Label(framebtn, text="Điểm miệng").grid(row=0, column=4, padx=10, pady=10, sticky="w")
        entry_diem_mieng = tk.Entry(framebtn)
        entry_diem_mieng.grid(row=0, column=5, padx=30, pady=10)

        # Điểm 15 phút
        tk.Label(framebtn, text="Điểm 15 phút").grid(row=1, column=4, padx=10, pady=10, sticky="w")
        entry_diem_15p = tk.Entry(framebtn)
        entry_diem_15p.grid(row=1, column=5, padx=10, pady=10)

        # Điểm 1 tiết
        tk.Label(framebtn, text="Điểm 1 tiết").grid(row=2 ,column=4, padx=10, pady=10, sticky="w")
        entry_diem_1tiet = tk.Entry(framebtn)
        entry_diem_1tiet.grid(row=2, column=5, padx=10, pady=10)

        # Điểm học kỳ
        tk.Label(framebtn, text="Điểm học kỳ").grid(row=3, column=4, padx=10, pady=10, sticky="w")
        entry_diem_hk = tk.Entry(framebtn)
        entry_diem_hk.grid(row=3, column=5, padx=10, pady=10)

        def lay_ds_hoc_sinh():
            cur.execute("SELECT MaHocSinh, HoTen FROM HocSinh")
            return cur.fetchall()

        def lay_ds_mon_hoc():
            cur.execute("SELECT MaMH, TenMH FROM MonHoc")
            return cur.fetchall()

        def tinh_diem_tb( mieng, diem15p, diem1tiet, diem_hk):
            return round((mieng + diem15p + diem1tiet * 2 + diem_hk * 3) / 7, 2)

        def cap_nhat_combobox():
            """Cập nhật dữ liệu cho Combobox"""
            ds_hs = [f"{hs[0]} - {hs[1]}" for hs in lay_ds_hoc_sinh()]
            ds_mh = [f"{mh[0]} - {mh[1]}" for mh in lay_ds_mon_hoc()]

            cmb_hs["values"] = ds_hs
            cmb_mh["values"] = ds_mh

        def timkiem():
            framepage = tk.Frame(padx=190, pady=0, width=811, height=561)
            framepage.place(x=229, y=0)

            table = FrameSearchDiem(framepage)
            table.place(width=300, height=565)

        def hien_thi_diem():
            """Hiển thị điểm trong bảng"""
            for row in self.tree.get_children():
                self.tree.delete(row)

            cur.execute("""
               SELECT DiemSo.MaHocSinh, HocSinh.HoTen, Monhoc.TenMH, DiemSo.HocKy, 
                          DiemSo.DiemMieng, DiemSo.Diem15Phut, DiemSo.Diem1Tiet, 
                          DiemSo.DiemHocKy, DiemSo.DiemTrungBinh
                   FROM DiemSo
                   INNER JOIN HocSinh ON DiemSo.MaHocSinh = HocSinh.MaHocSinh
                    JOIN Monhoc ON DiemSo.MaMonHoc = Monhoc.MaMH
                   """)

            for row in cur.fetchall():
                self.tree.insert("", "end", values=row)
                self.tree.pack(pady=10)

        def them_diem():
            try:
                if not cmb_hs.get() or not cmb_mh.get() or not cmb_hocky.get():
                    messagebox.showwarning("Cảnh báo",
                                           "Vui lòng chọn học sinh, môn học và học kỳ!")
                    return
                for diem in [entry_diem_mieng.get(), entry_diem_15p.get(), entry_diem_1tiet.get(),
                             entry_diem_hk.get()]:
                    if not diem.replace('.', '', 1).isdigit() or not (0 <= float(diem) <= 10):
                        messagebox.showwarning("Cảnh báo", "Điểm phải là số trong khoảng từ 0 đến 10!")
                        return
                diem_tb = tinh_diem_tb(
                    float(entry_diem_mieng.get()),
                    float(entry_diem_15p.get()),
                    float(entry_diem_1tiet.get()),
                    float(entry_diem_hk.get())
                )
                cur.execute('''
                       INSERT INTO DiemSo (MaHocSinh, MaMonHoc, HocKy, DiemMieng, Diem15Phut, Diem1Tiet, DiemHocKy, DiemTrungBinh)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                       ''', (
                    cmb_hs.get().split(' - ')[0],
                    cmb_mh.get().split(' - ')[0],
                    cmb_hocky.get(),
                    float(entry_diem_mieng.get()),
                    float(entry_diem_15p.get()),
                    float(entry_diem_1tiet.get()),
                    float(entry_diem_hk.get()),
                    diem_tb
                ))
                conn.commit()
                hien_thi_diem()
                messagebox.showinfo("Thành công", "Thêm điểm thành công")
                clear_entry()
            except Exception as e:
                messagebox.showerror("Lỗi", f"Lỗi thêm điểm: {e}")

        def sua_diem():
            try:
                selected_item = self.tree.selection()[0]
                values = self.tree.item(selected_item, "values")
                cur.execute('''
                       UPDATE DiemSo SET DiemMieng=?, Diem15Phut=?, Diem1Tiet=?, DiemHocKy=?, DiemTrungBinh=?
                       WHERE MaHocSinh=? AND MaMonHoc=? AND HocKy=?
                       ''', (
                    float(entry_diem_mieng.get()),
                    float(entry_diem_15p.get()),
                    float(entry_diem_1tiet.get()),
                    float(entry_diem_hk.get()),
                    tinh_diem_tb(
                        float(entry_diem_mieng.get()),
                        float(entry_diem_15p.get()),
                        float(entry_diem_1tiet.get()),
                        float(entry_diem_hk.get())
                    ),
                    values[0], values[2], values[3]
                ))
                conn.commit()
                hien_thi_diem()
                messagebox.showinfo("Thành công", "Sửa điểm thành công")
                clear_entry()
            except Exception as e:
                messagebox.showerror("Lỗi", f"Lỗi sửa điểm: {e}")

        def xoa_diem():
            try:
                selected_item = self.tree.selection()[0]
                values = self.tree.item(selected_item, "values")
                cur.execute("DELETE FROM DiemSo WHERE MaHocSinh=? AND MaMonHoc=? AND HocKy=?",
                            (values[0], values[2], values[3]))
                conn.commit()
                hien_thi_diem()
                messagebox.showinfo("Thành công", "Xóa điểm thành công")
                clear_entry()
            except Exception as e:
                messagebox.showerror("Lỗi", f"Lỗi xóa điểm: {e}")

        def chon_dong(_):
            """Chọn dòng trong Treeview"""
            try:
                selected_item = self.tree.selection()[0]
                values = self.tree.item(selected_item, "values")

                cmb_hs.set(f"{values[0]} - {values[1]}")
                cmb_mh.set(f"{values[2]}")
                cmb_hocky.set(values[3])
                entry_diem_mieng.delete(0, tk.END)
                entry_diem_mieng.insert(0, values[4])
                entry_diem_15p.delete(0, tk.END)
                entry_diem_15p.insert(0, values[4])
                entry_diem_1tiet.delete(0, tk.END)
                entry_diem_1tiet.insert(0, values[4])
                entry_diem_hk.delete(0, tk.END)
                entry_diem_hk.insert(0, values[4])
            except IndexError:
                pass
        def clear_entry():
            cmb_hs.set("")
            cmb_mh.set("")
            cmb_hocky.set("")
            entry_diem_mieng.delete(0, tk.END)
            entry_diem_15p.delete(0, tk.END)
            entry_diem_1tiet.delete(0, tk.END)
            entry_diem_hk.delete(0, tk.END)
        # iconn
        icon_update = ImageTk.PhotoImage(file="icon/update.png")
        icon_add = ImageTk.PhotoImage(file="icon/add.png")
        icon_delete = ImageTk.PhotoImage(file="icon/delete .png")
        icon_search = ImageTk.PhotoImage(file="icon/search.png")
        # Nút thao tác
        btn_add = tk.Button(framebtn, text="Thêm    ", font=("Arial", 11), image=icon_add, compound='right',
                              command=them_diem, width=100, height=25, background='#65B446', fg='#FFFFFF')
        btn_add.image = icon_add

        btn_update = tk.Button(framebtn, text="Sửa  ", font=("Arial", 11), image=icon_update, compound='right'
                                 , width=100, height=25, background="#F1AF00", fg="#FFFFFF", command=sua_diem)
        btn_update.image = icon_update
        btn_delete = tk.Button(framebtn, text="Xóa  ", font=("Arial", 11), image=icon_delete, compound='right'
                                 , width=100, height=25, fg="#FFFFFF", background="#C82E31", command=xoa_diem)
        btn_delete.image = icon_delete
        btn_search = tk.Button(framebtn, text="Tìm Kiếm  ", font=("Arial", 11), image=icon_search, compound='right'
                               , width=100, height=25, fg="#FFFFFF", background="#00676B",command=timkiem)
        btn_search.image = icon_search
        btn_add.grid(row=5, column=0, padx=0, pady=10)
        btn_update.grid(row=5, column=1, padx=0, pady=10)
        btn_delete.grid(row=5, column=2, padx=0, pady=10)
        btn_search.grid(row=5, column=5, padx=0, pady=10)

        # Gán sự kiện chọn dòng
        self.tree.bind("<<TreeviewSelect>>", chon_dong)

        # Cập nhật dữ liệu
        cap_nhat_combobox()
        hien_thi_diem()
