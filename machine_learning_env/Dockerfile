 FROM ubuntu:latest
 
 #COPY apt.conf /etc/apt/apt.conf
 
 ENV http_proxy=http://IP address:port


 
 LABEL maintainer="Simphiwe"
 #RUN cat /etc/resolv.conf
 #RUN apt-get update && apt-get install -y apache2 && rm -rf /var/lib/apt/lists/*
 RUN apt-get update 
 #RUN apt-get clean 
 #RUN apt-get upgrade
 
 # Pick up some dependancies
 #RUN apt-get -y install wget
 RUN apt-get -y install apache2
 RUN apt-get -y install software-properties-common
 RUN apt-get -y install curl
 RUN apt-get install -y unzip
 RUN apt-get install -y build-essential
 RUN apt-get install -y libfreetype6-dev
 RUN apt-get install -y libhdf5-serial-dev
 RUN apt-get install -y libpng-dev
 RUN apt-get install -y libzmq3-dev
 RUN apt-get install -y pkg-config
 RUN apt-get install -y vim
 RUN apt-get -y install python3-pip
 RUN apt-get -y install python-pip
 RUN apt-get install -y git
 RUN apt-get install -y python3-dev 
 RUN apt-get install -y python3-numpy
 RUN apt-get install -y libsm6 libxext6
 RUN apt-get install -y libfontconfig1 libxrender1
 RUN apt-get install -y python3-yaml


 RUN pip3 install Pillow
 RUN pip3 install pyyaml
 RUN pip3 install --upgrade pip
 RUN pip3  install Pillow
 RUN pip3 install --upgrade setuptools
 RUN pip3 install h5py
 RUN pip3 install ipykernel
 RUN pip3 install jupyter
 RUN pip3 install matplotlib
 #Fix numpy to 1.13.3
 RUN pip3 install numpy==1.13.3
 RUN pip3 install pandas
 RUN pip3 install scipy
 RUN pip3 install sklearn
 RUN pip3 install keras
 RUN pip3 install opencv-python
 RUN pip3 install scikit-image
 RUN pip3 install tensorflow==1.7.0
 RUN python3 -m ipykernel.kernelspec
 RUN pip3 install seaborn


 #Build torch-vision from source
 RUN git clone https://github.com/pytorch/vision.git \
  && cd vision \
  && git checkout 83263d8571c9cdd46f250a7986a5219ed29d19a1 \
  && python setup.py install \
  && rm -rf vision 
 
 # For Jupyter notebook
 EXPOSE 8888
 #For TensorBoard
 EXPOSE 6006

 
 WORKDIR ~/jupyter_conf

 #Setup Tensorflow object detection
 RUN git clone https://github.com/tensorflow/models.git

 # RUN apt-get install -y protobuf-compiler
 RUN pip3 install Cython
 RUN pip3 install lxml

 # Add SNI support to Python
 RUN pip3 --no-cache-dir install \
     pyopenssl \
     ndg-httpsclient \
     pyasn1

 #install Caffe
 # https://github.com/tensorflow/models/issues/4002
 # Installing Protobuf
 RUN curl -OL https://github.com/google/protobuf/releases/download/v3.2.0/protoc-3.2.0-linux-x86_64.zip
 RUN unzip protoc-3.2.0-linux-x86_64.zip -d protoc3
 RUN mv protoc3/bin/* /usr/local/bin/
 RUN mv protoc3/include/* /usr/local/include/
 RUN rm protoc-3.2.0-linux-x86_64.zip
 RUN rm -rf protoc3

 # RUN cd models/research
 # RUN protoc ./models/research/object_detection/protos/*.proto --python_out=.
 RUN cd models/research && protoc ./object_detection/protos/*.proto --python_out=.

 #From tensorflow/models/research/
 RUN echo 'export PYTHONPATH=$PYTHONPATH:`pwd`:`pwd`/slim' >> ~/.bashrc

 #setup notebook configs
 COPY ./jupyter_conf/jupyter_notebook_config.py /root/.jupyter/jupyter_notebook_config.py

 ENV PASSWORD cib

 CMD ["jupyter", "notebook", "--allow-root"]
