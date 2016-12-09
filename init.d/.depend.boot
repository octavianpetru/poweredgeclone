TARGETS = console-setup mountkernfs.sh resolvconf ufw hostname.sh apparmor screen-cleanup plymouth-log udev keyboard-setup mountdevsubfs.sh procps cryptdisks cryptdisks-early hwclock.sh networking urandom checkroot.sh lvm2 open-iscsi iscsid checkfs.sh mountall-bootclean.sh mountall.sh bootmisc.sh mountnfs-bootclean.sh mountnfs.sh checkroot-bootclean.sh kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
networking: mountkernfs.sh urandom resolvconf procps
urandom: hwclock.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
open-iscsi: networking iscsid
iscsid: networking
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
bootmisc.sh: mountall-bootclean.sh mountnfs-bootclean.sh udev checkroot-bootclean.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
