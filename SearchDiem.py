import sqlite3
import tkinter as tk
from tkinter import ttk, messagebox, Entry
from tracemalloc import Frame
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle
from reportlab.lib import colors

from PIL import ImageTk


class FrameSearchDiem(ttk.Frame):

    def __init__(self, parent):
        super().__init__(parent)

        # Kết nối CSDL và lưu vào self để dùng sau
        conn = sqlite3.connect("students.db")
        cur = conn.cursor()

        cur.execute('''
        CREATE TABLE IF NOT EXISTS DiemSo (
            MaHocSinh INTEGER,
            MaMonHoc INTEGER,
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
            self.tree.column(col, width=85, anchor="center")

        self.tree.pack(pady=10)

        # Giao diện btn
        # Mã học sinh
        tk.Label(framebtn, text="Mã học sinh").grid(row=0, column=0, padx=10, pady=10, sticky="w")
        cmb_hs = ttk.Entry(framebtn)
        cmb_hs.grid(row=0, column=1, padx=10, pady=15)

        # Mã môn học
        tk.Label(framebtn, text="Mã lớp học").grid(row=0, column=3, padx=10, pady=15, sticky="w")
        cmb_ml = ttk.Combobox(framebtn)
        cmb_ml.grid(row=0, column=4, padx=10, pady=15)

        def lay_ds_hoc_sinh():
            cur.execute("SELECT MaHocSinh, HoTen FROM HocSinh")
            return cur.fetchall()

        def lay_ds_lop_hoc():
            cur.execute("SELECT MaLop, TenLop FROM LopHoc")
            return cur.fetchall()



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

        def load_danh_sach_lop():
                cur.execute("SELECT MaLop, TenLop FROM LopHoc")
                rows = cur.fetchall()
                cmb_ml["values"] = [f"{r[0]} - {r[1]}" for r in rows]

        def xuat_pdf():
            try:
                # Tạo file PDF mới
                pdf_file = "DanhSachDiem.pdf"
                pdf = SimpleDocTemplate(pdf_file, pagesize=letter)
                cur.execute("""
                              SELECT DiemSo.MaHocSinh, HocSinh.HoTen, Monhoc.TenMH, DiemSo.HocKy, 
                                         DiemSo.DiemMieng, DiemSo.Diem15Phut, DiemSo.Diem1Tiet, 
                                         DiemSo.DiemHocKy, DiemSo.DiemTrungBinh
                                  FROM DiemSo
                                  INNER JOIN HocSinh ON DiemSo.MaHocSinh = HocSinh.MaHocSinh
                                   JOIN Monhoc ON DiemSo.MaMonHoc = Monhoc.MaMH
                                  """)

                # Lấy dữ liệu từ database

                data = cur.fetchall()

                # Nếu không có dữ liệu thì báo lỗi
                if not data:
                    messagebox.showwarning("Lỗi", "Không có dữ liệu để xuất!")
                    return

                # Tiêu đề bảng
                colums = ["Mã HS", "Tên HS", "Tên MH", "HK", "Điểm Miệng","Điểm 15p", "1 Tiết","Điểm HK", "TB"]
                table_data = [colums] + data  # Ghép tiêu đề với dữ liệu

                # Tạo bảng
                table = Table(table_data)
                table.setStyle(TableStyle([
                    ('BACKGROUND', (0, 0), (-1, 0), colors.white),  # Màu nền tiêu đề
                    ('TEXTCOLOR', (0, 0), (-1, 0), colors.black),  # Màu chữ tiêu đề
                    ('ALIGN', (0, 0), (-1, -1), 'CENTER'),  # Căn giữa chữ
                    ('GRID', (0, 0), (-1, -1), 1, colors.black)  # Kẻ viền bảng
                ]))

                # Xuất file PDF
                pdf.build([table])
                messagebox.showinfo("Thành công", "Xuất file PDF thành công!")

            except Exception as e:
                messagebox.showerror("Lỗi", f"Không thể xuất PDF: {e}")


        def TimKiemTheoMaHS():
            mahs = cmb_hs.get().strip()
            malop = cmb_ml.get().strip()

            if not mahs and not malop:
                messagebox.showwarning("Cảnh báo", "Vui lòng nhập mã học sinh hoặc mã lớp!")
                return

            if mahs:
                cur.execute("""
                               SELECT DiemSo.MaHocSinh, HocSinh.HoTen, Monhoc.TenMH, DiemSo.HocKy, 
                                          DiemSo.DiemMieng, DiemSo.Diem15Phut, DiemSo.Diem1Tiet, 
                                          DiemSo.DiemHocKy, DiemSo.DiemTrungBinh
                                   FROM DiemSo
                                   INNER JOIN HocSinh ON DiemSo.MaHocSinh = HocSinh.MaHocSinh
                                    JOIN Monhoc ON DiemSo.MaMonHoc = Monhoc.MaMH
                                    WHERE DiemSo.MaHocSinh = ?
                                   """,(mahs,))
            else:
                cur.execute("""
                               SELECT DiemSo.MaHocSinh, HocSinh.HoTen, Monhoc.TenMH, DiemSo.HocKy, 
                                          DiemSo.DiemMieng, DiemSo.Diem15Phut, DiemSo.Diem1Tiet, 
                                          DiemSo.DiemHocKy, DiemSo.DiemTrungBinh
                                   FROM DiemSo
                                   INNER JOIN HocSinh ON DiemSo.MaHocSinh = HocSinh.MaHocSinh
                                    JOIN Monhoc ON DiemSo.MaMonHoc = Monhoc.MaMH
                                    
                                    WHERE HocSinh.MaLop = ?
                                   """,(malop,))

            rows = cur.fetchall()



            # Xóa dữ liệu cũ trong Treeview
            for row in self.tree.get_children():
                self.tree.delete(row)


            if not rows:
                messagebox.showinfo("Thông báo", "Không tìm thấy thông tin điểm số !")
                return

            # Hiển thị kết quả tìm kiếm
            for row in rows:
                self.tree.insert("", "end", values=row)
                self.tree.pack(pady=10)

            # Chỉ xóa nội dung trong Entry

            cmb_hs.delete(0,tk.END)
            cmb_ml.set("")

        def clear_entry():
            cmb_hs.delete(0,tk.END)
            cmb_ml.delete(0,tk.END)

        # iconn

        icon_export = ImageTk.PhotoImage(file="icon/export.png")
        icon_search = ImageTk.PhotoImage(file="icon/search.png")
        # Nút thao tác
        btn_search = tk.Button(framebtn, text="Tìm Kiếm    ", font=("Arial", 11), image=icon_search, compound='right',
                               width=110, height=25, background='#00676B', fg='#FFFFFF', command=TimKiemTheoMaHS)
        btn_search.image = icon_search

        btn_export = tk.Button(framebtn, text="Xuất PDF  ", font=("Arial", 11), image=icon_export, compound='right'
                               , width=100, height=25, fg="#FFFFFF", background="#AA87B8",command=xuat_pdf)
        btn_export.image = icon_export

        btn_search.grid(row=5, column=0, padx=0, pady=10)
        btn_export.grid(row=5, column=1, padx=0, pady=10)

        # Cập nhật dữ liệu
        load_danh_sach_lop()
        hien_thi_diem()
