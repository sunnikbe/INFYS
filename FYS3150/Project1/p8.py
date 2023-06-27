import numpy as np
import matplotlib.pyplot as plt

# a) Plotting log of abs err

u_txt_files = ("p210.txt", "p2.txt", "p21000.txt", "p210000.txt")
v_txt_files = ("p7.txt", "p7100.txt", "p71000.txt", "p710000.txt")
n_steps = (r"$n_{steps} = 10$", r"$n_{steps} = 10^2$", r"$n_{steps} = 10^3$", r"$n_{steps} = 10^4$")

for i in range(len(n_steps)):
    # u_i data
    u_data = np.loadtxt(u_txt_files[i])
    x = u_data[:, 0] # x - vector
    u_i = u_data[:, 1] # u(x) values

    # v_i data
    v_data = np.loadtxt(v_txt_files[i])
    v_i = v_data[:, 0] # v(x) values

    # slice lists, take away first and last value
    x = x[1:-1]
    u_i = u_i[1:-1]
    v_i = v_i[1:-1]

    # making the abs(u_i - v_i) list
    u_i_v_i = []
    for k in range(len(v_i)):
        u_i_v_i.append(np.abs(u_i[k] - v_i[k]))

    #plotting everything
    plt.plot(x, u_i_v_i, label = n_steps[i])

plt.title("Absolute error")
plt.xlabel(r"$x_i$")
plt.ylabel(r"$\Delta_i$ (logarithmic scale) ")
plt.yscale("log")
plt.legend()
plt.savefig('p8.pdf')
