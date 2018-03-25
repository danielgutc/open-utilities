FROM jenkins/jenkins:lst
USER ROOT
RUN apt-get install nfs-common
RUN mkdir -p /mnt/nfs
RUN echo '192.168.65.1:/home/daniel/HomeDataCenter/nfs /mnt/nfs nfs rsize=8192,wsize=8192,timeo=14,intr' >> /etc/fstab
RUN mount /mnt/nfs
ENV EXT_MOUNT=/mnt/nfs
VOLUME [/mnt/nfs]
USER JENKINS
ENTRYPOINT ["/bin/tini" "--" "/usr/local/bin/jenkins.sh"]