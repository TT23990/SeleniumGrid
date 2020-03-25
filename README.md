# SeleniumGrid
Linux command for Amazon ekkanda1$
Putty for windows (chmod 400 to to access file readonly by admin)
1. User name any user(Ubuntu or ec2-user)
2. uname -r (to check linux version)
2. Java -version
3. become root user (Sudo su)
4. sudo yum list | grep java-1.8
4. intall java 1.8 for Jenkins (yum install java-1.8.0-openjdk-devel -y)
5. sudo update-alternatives --config java  and select 2 to for java 1.8 as default
5. goto http://pkg.jenkins.io/ (redhat stable)

sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key

yum install jenkins -y

6. sudo service jenkins start or systemctl start jenkins  to start jenkins
7. sudo chkconfig jenkins on or (enable jenkins) to run jenkins at boot time 
8. systemctl status jenkins
9. cat copy path from Jenins to get first password

10. clear

user : Jenkins1 - start



https://docs.docker.com/install/linux/docker-ce/ubuntu/
Intalling Docker on linux

1. sudo yum -y update
2. sudo yum install -y docker
3. docker --version
4. docker info  (can not connect, need to start docker)
5. sudo service docker start 
6. sudo service docker stop
7. sudo yum remove docker
https://opensource.zalando.com/zalenium/

8. docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start

Zallenium setup

http://52.15.109.190:4444/grid/console
http://52.15.109.190:4444/grid/admin/live
